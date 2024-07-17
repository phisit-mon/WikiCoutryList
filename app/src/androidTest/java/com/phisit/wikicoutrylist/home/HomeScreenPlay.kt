package com.phisit.wikicoutrylist.home

import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick

class HomeScreenPlay(
    private val screenObject: HomeScreenObject
) {

    fun loadingDisplayed(): Boolean {
        return screenObject.loading.isDisplayed()
    }

    fun errorDisplayed(): Boolean {
        return screenObject.error.isDisplayed()
    }

    fun countryListDisplayed(): Boolean {
        return screenObject.countyList.isDisplayed()
    }

    fun countryListDisplaySizeCorrectly(size: Int): Boolean {
        return getCountryItemsCollection().assertCountEquals(size).onFirst().isDisplayed()
    }

    fun getCountryItemsCollection(): SemanticsNodeInteractionCollection {
        return screenObject.countyListItems
    }

    fun countryItemsCollectionDisplayed(country: String): Boolean {
        return getCountryItemsCollection()
            .filter(hasText(country, substring = true))
            .onFirst()
            .isDisplayed()
    }

    fun clickCountryItem(country: String) {
        getCountryItemsCollection()
            .filter(hasAnyChild(hasText(country)))
            .onFirst()
            .performClick()
    }
}