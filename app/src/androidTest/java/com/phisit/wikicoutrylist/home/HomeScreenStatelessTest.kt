package com.phisit.wikicoutrylist.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.phisit.wikicoutrylist.domain.CountryModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenStatelessTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var uiObject: HomeScreenObject
    private lateinit var player: HomeScreenPlay

    @Before
    fun init() {
        uiObject = HomeScreenObject(composeTestRule)
        player = HomeScreenPlay(uiObject)
    }

    @Test
    fun verifyLoadingState() {
        composeTestRule.setContent {
            HomeScreenStateless(uiState = HomeUiState.Loading) {}
        }
        player.loadingDisplayed()
    }

    @Test
    fun verifyErrorState() {
        composeTestRule.setContent {
            HomeScreenStateless(uiState = HomeUiState.Error) {}
        }
        player.errorDisplayed()
    }

    @Test
    fun verifyCountryListState() {
        composeTestRule.setContent {
            HomeScreenStateless(uiState = HomeUiState.CountyList(listOf())) {}
        }
        player.countryListDisplayed()
//        composeTestRule.onRoot().printToLog("verifyCountryListState")
    }

    @Test
    fun verifyChildrenCountryList() {
        val uiState = HomeUiState.CountyList(
            listOf(
                CountryModel("1", "Thailand", "Asia"),
                CountryModel("2", "Japan", "Asia"),
                CountryModel("3", "Korea", "Asia"),
                CountryModel("4", "Costa Rica", "North America"),
            )
        )

        composeTestRule.setContent {
            HomeScreenStateless(uiState = uiState) {}
        }
        with(player){
            countryListDisplayed()
            countryListDisplaySizeCorrectly(uiState.countries.size)
        }
    }
}