package com.phisit.wikicoutrylist.home

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.intent.Intents
import androidx.test.ext.truth.content.IntentSubject
import com.phisit.wikicoutrylist.MainActivity
import com.phisit.wikicoutrylist.webview.WikiWebViewActivity
import com.phisit.wikicoutrylist.base.BaseScreenTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest: BaseScreenTest() {

  /*  @get:Rule
    val composeTestRule = createEmptyComposeRule()*/

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    lateinit var screenObj: HomeScreenObject
    lateinit var screenPlay: HomeScreenPlay
    lateinit var navController: TestNavHostController

    @Before
    fun initialComposeRule() {
       /* composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            HomeScreen(navController = navController, viewModel = HomeViewModel())
        }*/
        screenObj = HomeScreenObject(composeTestRule)
        screenPlay = HomeScreenPlay(screenObj)
    }

   /* @Test
    fun verifyLoadingState() {
//        launchApp<MainActivity>()
        *//* composeTestRule.setContent {
             HomeScreenStateless(uiState = HomeUiState.Loading) {}
         }*//*
//        composeTestRule.onNodeWithTag("loading").assertIsDisplayed().assertExists()
//        Thread.sleep(5000)
        *//*composeTestRule.onNodeWithTag("loading").assertExists()
        composeTestRule.waitUntil {
            composeTestRule.onNodeWithTag("country_list")
                .isDisplayed()
        }*//*
//        screenPlay.showLoading()
//        Intents.init()

        composeTestRule.onNode(
            hasText("Open Second Activity").and(hasClickAction())
        ).performClick()

        composeTestRule.onNode(
            hasText("Hello world Second Activity")
        ).assertExists()
            .isDisplayed()

        // Checking activity in backstack
        IntentSubject.assertThat( Intents.getIntents().first()).hasComponentClass(WikiWebViewActivity::class.java)

    }*/
}