package com.phisit.wikicoutrylist.search

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.phisit.wikicoutrylist.domain.CountryModel
import com.phisit.wikicoutrylist.home.CountryList
import com.phisit.wikicoutrylist.ui.theme.WikiCoutryListTheme
import com.phisit.wikicoutrylist.webview.WikiWebViewActivity
import com.phisit.wikicoutrylist.widget.SearchBar
import org.koin.androidx.compose.koinViewModel

@SuppressLint("RememberReturnType")
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {

    val listState = rememberLazyListState()

    var searchingText by remember {
        mutableStateOf("")
    }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.searchCountries(searchingText)
    }

    WikiCoutryListTheme {
        Column(
            modifier.fillMaxSize()
        ) {
            SearchBar(
                value = searchingText,
                clearFocus = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                keyboardAction = KeyboardActions(
                    onSearch = {
                        viewModel.searchCountries(searchingText)
                    }
                )
            ){
                searchingText = it
            }

            SearchScreenStateless(
                uiState = uiState.value,
                listState = listState
            ) {
                val intent = Intent(context, WikiWebViewActivity::class.java).apply {
                    putExtra(WikiWebViewActivity.COUNTRY_INTENT_EXTRA, it.countryName)
                }
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun SearchScreenStateless(
    uiState: SearchUiState,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    onClickedCountryList: (CountryModel) -> Unit
) {
    when (uiState) {
        is SearchUiState.CountyList -> {
            CountryList(
                countries = uiState.countries,
                listState = listState,
                onClicked = {
                    onClickedCountryList(it)
                })
        }

        is SearchUiState.Error -> {
            Box(modifier.fillMaxSize()) {
                Text(
                    text = "Somethings went wrong!!!",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("error")
                )
            }
        }

        is SearchUiState.EmptyList -> {
            Box(modifier.fillMaxSize()) {
                Text(
                    text = "No Result",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("empty")
                )
            }
        }

        else -> {
            Box(modifier.fillMaxSize()) {
                Text(
                    text = "Loading..",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("loading")
                )
            }
        }
    }
}