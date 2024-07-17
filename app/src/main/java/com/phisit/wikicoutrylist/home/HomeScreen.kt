package com.phisit.wikicoutrylist.home

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.phisit.wikicoutrylist.CountryItem
import com.phisit.wikicoutrylist.domain.CountryModel
import com.phisit.wikicoutrylist.domain.fakeCountries
import com.phisit.wikicoutrylist.webview.WikiWebViewActivity
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (viewModel.uiState.value !is HomeUiState.CountyList) {
            viewModel.getCounties()
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Countries In the world",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

        HomeScreenStateless(uiState.value) {
            val intent = Intent(context, WikiWebViewActivity::class.java).apply {
                putExtra(WikiWebViewActivity.COUNTRY_INTENT_EXTRA, it.countryName)
            }
            context.startActivity(intent)
        }
    }
}

@Composable
fun HomeScreenStateless(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    onClickedCountryList: (CountryModel) -> Unit
) {
    when (uiState) {
        is HomeUiState.CountyList -> {
            CountryList(countries = uiState.countries, onClicked = {
                onClickedCountryList(it)
            })
        }

        is HomeUiState.Error -> {
            Box(modifier.fillMaxSize()) {
                Text(
                    text = "Somethings went wrong!!!",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("error")
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

@Composable
fun CountryList(
    countries: List<CountryModel>,
    listState: LazyListState = rememberLazyListState(),
    onClicked: (CountryModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .testTag("country_list"),
        state = listState
    ) {
        itemsIndexed(
            countries, { index, item -> item.id })
        { index, item ->
            CountryItem(
                country = item.countryName,
                landmass = item.landMass,
                modifier = Modifier
                    .clickable {
                        onClicked(item)
                    }
                    .clearAndSetSemantics {
                        contentDescription = "country_item_$index"
                    }
            )
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryListPreview() {
    CountryList(fakeCountries, onClicked = {})
}