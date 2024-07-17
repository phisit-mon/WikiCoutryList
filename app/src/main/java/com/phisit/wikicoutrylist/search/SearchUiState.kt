package com.phisit.wikicoutrylist.search

import com.phisit.wikicoutrylist.domain.CountryModel

sealed class SearchUiState {

    object EmptyList : SearchUiState()
    object Error : SearchUiState()
    object Loading : SearchUiState()

    data class CountyList(
        val searchText: String,
        val countries: List<CountryModel>
    ) : SearchUiState()
}