package com.phisit.wikicoutrylist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phisit.wikicoutrylist.data.CountryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    val uiState: StateFlow<HomeUiState> = _uiState

    fun getCounties() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            runCatching {
                delay(1000)
                _uiState.value = HomeUiState.CountyList(countryRepository.getCountryList())
            }.onFailure {
                _uiState.value = HomeUiState.Error
            }
        }
    }
}