package com.phisit.wikicoutrylist.data

import com.phisit.wikicoutrylist.domain.CountryModel
import com.phisit.wikicoutrylist.domain.fakeCountries

class CountryRepositoryImpl: CountryRepository {

    override suspend fun getCountryList(): List<CountryModel> {
        return fakeCountries
    }
}