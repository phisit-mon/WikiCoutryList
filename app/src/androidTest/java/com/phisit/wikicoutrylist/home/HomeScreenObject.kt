package com.phisit.wikicoutrylist.home

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasContentDescriptionExactly
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag

class HomeScreenObject(
    private val composeTestRule: ComposeTestRule
) {

    val error get() = composeTestRule.onNodeWithTag("error")

    val loading get() = composeTestRule.onNodeWithTag("loading")

    val countyList get() = composeTestRule.onNodeWithTag("country_list")

    val countyListItems get() = composeTestRule.onAllNodes(
        hasParent(hasTestTag("country_list"))
            .and(hasClickAction())
            .and(hasContentDescription("country_item_", substring = true)),
        useUnmergedTree = true
    )

}