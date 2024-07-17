package com.phisit.wikicoutrylist.data

import com.phisit.wikicoutrylist.domain.CountryModel

class CountryRepositoryImpl : CountryRepository {

    override suspend fun getCountryList(): List<CountryModel> {
        return listOf(
            CountryModel("1", "Thailand", "Asia"),
            CountryModel("2", "Japan", "Asia"),
            CountryModel("3", "Korea", "Asia"),
            CountryModel("4", "Costa Rica", "North America")
        )
    }
}