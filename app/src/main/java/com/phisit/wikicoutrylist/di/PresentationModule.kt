package com.phisit.wikicoutrylist.di

import com.phisit.wikicoutrylist.data.CountryRepository
import com.phisit.wikicoutrylist.data.CountryRepositoryImpl
import com.phisit.wikicoutrylist.home.HomeViewModel
import com.phisit.wikicoutrylist.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory<CountryRepository> { CountryRepositoryImpl() }

    viewModel<HomeViewModel> {
        HomeViewModel(get())
    }

    viewModel<SearchViewModel> {
        SearchViewModel(get())
    }
}