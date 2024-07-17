package com.phisit.wikicoutrylist.di

import com.phisit.wikicoutrylist.data.CountryRepository
import com.phisit.wikicoutrylist.data.CountryRepositoryImpl
import org.koin.dsl.module

val testModule = module {
    factory<CountryRepository> {
        CountryRepositoryImpl()
    }
}