package com.phisit.wikicoutrylist.data

import com.phisit.wikicoutrylist.domain.CountryModel

interface CountryRepository {
    suspend fun getCountryList(): List<CountryModel>
}