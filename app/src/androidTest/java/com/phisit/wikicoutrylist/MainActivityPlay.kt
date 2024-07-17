package com.phisit.wikicoutrylist

import androidx.compose.ui.test.performClick

class MainActivityPlay(
    private val screenObject: MainActivityObject
) {

    fun clickHomeButtonNav() {
        screenObject.homeBottomNav.performClick()
    }

    fun clickSearchButtonNav() {
        screenObject.searchBottomNav.performClick()
    }

}