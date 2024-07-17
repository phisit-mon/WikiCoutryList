package com.phisit.wikicoutrylist.webview

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag

class WikiWebViewScreenObject(
    private val composeTestRule: ComposeTestRule
) {

    fun topBarLayout() = composeTestRule.onNodeWithTag("toolbar_layout")

    fun topBarTitleText(country: String): SemanticsNodeInteraction {
        return composeTestRule.onNode(
            hasParent(hasTestTag("toolbar_layout"))
                .and(hasText(country))
        )
    }
}