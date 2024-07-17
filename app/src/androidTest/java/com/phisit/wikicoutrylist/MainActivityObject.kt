package com.phisit.wikicoutrylist

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag

class MainActivityObject(
    private val composeTestRule: ComposeTestRule
) {

    val homeBottomNav get() = composeTestRule.onNodeWithTag("HomeNavItem")

    val searchBottomNav get() = composeTestRule.onNodeWithTag("SearchNavItem")
}