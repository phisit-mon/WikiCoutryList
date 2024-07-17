package com.phisit.wikicoutrylist

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject
import com.phisit.wikicoutrylist.base.BaseScreenTest
import com.phisit.wikicoutrylist.di.testModule
import com.phisit.wikicoutrylist.home.HomeScreenObject
import com.phisit.wikicoutrylist.home.HomeScreenPlay
import com.phisit.wikicoutrylist.webview.WikiWebViewActivity
import com.phisit.wikicoutrylist.webview.WikiWebViewScreenObject
import com.phisit.wikicoutrylist.webview.WikiWebViewScreenPlay
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules

@RunWith(AndroidJUnit4::class)
class MainActivityHomeTabTest : BaseScreenTest() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    lateinit var homeScreenObject: HomeScreenObject
    lateinit var homeScreenPlayer: HomeScreenPlay

    override fun initial() {
        super.initial()
        homeScreenObject = HomeScreenObject(composeTestRule)
        homeScreenPlayer = HomeScreenPlay(homeScreenObject)

        // override with mocking repository
        loadKoinModules(testModule)
    }

    @Test
    fun home_verifyShowingCountryList() {
        homeScreenPlayer.loadingDisplayed()
        composeTestRule.waitUntil {
            homeScreenPlayer.countryListDisplayed()
                    && homeScreenPlayer.countryListDisplaySizeCorrectly(4)
        }
    }

    @Test
    fun home_verifyClickCountryItem_shouldOpenWebViewActivityCountryCorrectly() {
        homeScreenPlayer.loadingDisplayed()
        composeTestRule.waitUntil {
            homeScreenPlayer.countryListDisplayed()
        }
        homeScreenPlayer.clickCountryItem("Korea")

        IntentSubject.assertThat(Intents.getIntents().first())
            .hasComponentClass(WikiWebViewActivity::class.java)

        WikiWebViewScreenPlay(
            screenObject = WikiWebViewScreenObject(composeTestRule)
        ).apply {
            topBarLayoutDisplayed()
            topBarTitleTextDisplayed("Korea")
        }
    }
}