package com.phisit.wikicoutrylist

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject
import com.phisit.wikicoutrylist.base.BaseScreenTest
import com.phisit.wikicoutrylist.di.testModule
import com.phisit.wikicoutrylist.search.SearchScreenObject
import com.phisit.wikicoutrylist.search.SearchScreenPlay
import com.phisit.wikicoutrylist.webview.WikiWebViewActivity
import com.phisit.wikicoutrylist.webview.WikiWebViewScreenObject
import com.phisit.wikicoutrylist.webview.WikiWebViewScreenPlay
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules

@RunWith(AndroidJUnit4::class)
class MainActivitySearchTabTest : BaseScreenTest() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    lateinit var searchScreenObject: SearchScreenObject
    lateinit var searchScreenPlayer: SearchScreenPlay
    lateinit var mainScreenPlay: MainActivityPlay

    override fun initial() {
        super.initial()
        searchScreenObject = SearchScreenObject(composeTestRule)
        searchScreenPlayer = SearchScreenPlay(searchScreenObject)

        mainScreenPlay = MainActivityPlay(MainActivityObject(composeTestRule))

        // override with mocking repository
        loadKoinModules(testModule)

        search_verifyClickSearchBottomNav()
    }

    @Test
    fun search_verifyClickSearchBottomNav() {
        mainScreenPlay.clickSearchButtonNav()
    }

    @Test
    fun search_verifyClickCountryItem_shouldOpenWebViewActivityCountryCorrectly() {
        searchScreenPlayer.loadingDisplayed()
        composeTestRule.waitUntil {
            searchScreenPlayer.countryListDisplayed()
        }
        searchScreenPlayer.clickCountryItem("Thailand")

        IntentSubject.assertThat(Intents.getIntents().first())
            .hasComponentClass(WikiWebViewActivity::class.java)

        WikiWebViewScreenPlay(
            screenObject = WikiWebViewScreenObject(composeTestRule)
        ).apply {
            topBarLayoutDisplayed()
            topBarTitleTextDisplayed("Thailand")
        }
    }

    @Test
    fun search_verifySearchAndClickCountry_shouldOpenWebViewActivityCountryCorrectly() {
        searchScreenPlayer.loadingDisplayed()
        composeTestRule.waitUntil {
            searchScreenPlayer.countryListDisplayed()
        }
        searchScreenPlayer.searchTextField("Korea")
        searchScreenPlayer.searchTextFieldImeAction()
        composeTestRule.waitUntil {
            searchScreenPlayer.countryListDisplayed()
        }
        searchScreenPlayer.countryListDisplaySizeCorrectly(1)

        searchScreenPlayer.clickCountryItem("Korea")

        IntentSubject.assertThat(Intents.getIntents().first())
            .hasComponentClass(WikiWebViewActivity::class.java)

        WikiWebViewScreenPlay(
            screenObject = WikiWebViewScreenObject(composeTestRule)
        ).apply {
            topBarLayoutDisplayed()
            topBarTitleTextDisplayed("Korea")
        }
    }

    @Test
    fun search_verifySearchCountryWithOutResult_shouldShowEmptyState() {
        searchScreenPlayer.loadingDisplayed()
        composeTestRule.waitUntil {
            searchScreenPlayer.countryListDisplayed()
        }
        searchScreenPlayer.searchTextField("USA")
        searchScreenPlayer.searchTextFieldImeAction()

        composeTestRule.waitUntil {
            searchScreenPlayer.emptyResultDisplayed()
        }
    }
}