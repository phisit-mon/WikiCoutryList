package com.phisit.wikicoutrylist.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phisit.wikicoutrylist.data.CountryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)

    val uiState: StateFlow<SearchUiState> = _uiState

    fun searchCountries(country: String) {
        (_uiState.value as? SearchUiState.CountyList)?.let {
            if (country.equals(it.searchText)) return
        }

        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            runCatching {
                delay(1000)

                val countryList = countryRepository.getCountryList()

                if (country.isEmpty()) {
                    _uiState.value = SearchUiState.CountyList(country, countryList)
                } else {
                    countryList.filter {
                        it.countryName.contains(country, true)
                    }.let { filter ->
                        if (filter.isEmpty()) {
                            SearchUiState.EmptyList
                        } else {
                            SearchUiState.CountyList(country, filter)
                        }
                    }.let { _result ->
                        _uiState.value = _result
                    }
                }
            }.onFailure {
                _uiState.value = SearchUiState.Error
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("SearchViewModel", "onCleared")
    }
}