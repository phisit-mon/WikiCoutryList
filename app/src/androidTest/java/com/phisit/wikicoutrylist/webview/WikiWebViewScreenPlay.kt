package com.phisit.wikicoutrylist.webview

import androidx.compose.ui.test.isDisplayed

class WikiWebViewScreenPlay(
    private val screenObject: WikiWebViewScreenObject
) {

    fun topBarLayoutDisplayed(): Boolean {
        return screenObject.topBarLayout().isDisplayed()
    }

    fun topBarTitleTextDisplayed(country: String): Boolean {
        return screenObject
            .topBarTitleText(country)
            .assertExists()
            .isDisplayed()
    }
}