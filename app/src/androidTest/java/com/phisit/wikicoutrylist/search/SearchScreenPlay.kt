package com.phisit.wikicoutrylist.search

import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextInputSelection

class SearchScreenPlay(
    private val screenObject: SearchScreenObject
) {

    fun loadingDisplayed(): Boolean {
        return screenObject.loading.isDisplayed()
    }

    fun errorDisplayed(): Boolean {
        return screenObject.error.isDisplayed()
    }

    fun emptyResultDisplayed(): Boolean {
        return screenObject.empty.isDisplayed()
    }

    fun countryListDisplayed(): Boolean {
        return screenObject.countyList.isDisplayed()
    }

    fun searchTextField(country: String) {
        screenObject.searchTextField.performTextInput(country)
    }

    fun searchTextFieldImeAction() {
        screenObject.searchTextField.performImeAction()
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