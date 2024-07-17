package com.phisit.wikicoutrylist.home

import com.phisit.wikicoutrylist.domain.CountryModel

sealed class HomeUiState {

    object EmptyList : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()

    data class CountyList(
        val countries: List<CountryModel>
    ) : HomeUiState()
}
