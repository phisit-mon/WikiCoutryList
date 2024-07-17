package com.phisit.wikicoutrylist.widget

import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phisit.wikicoutrylist.ui.theme.WikiCoutryListTheme

@Composable
fun SearchBar(
    value: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enable: Boolean = true,
    clearFocus: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Search,
    keyboardAction: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit = {}
) {
    val focusRequester = remember {
        FocusRequester()
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Log.e("SearchBar", "clear focus value : $clearFocus")

    SideEffect {
        if (clearFocus) {
            focusManager?.clearFocus()
        } else {
            focusRequester.requestFocus()
        }
    }

    TextField(
        value = value,
        modifier = modifier
            .focusRequester(focusRequester)
            .testTag("search_text_field"),
        singleLine = true,
        enabled = enable,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.labelMedium
            )
        },
        onValueChange = onValueChanged,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Black
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = keyboardAction
    )
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    WikiCoutryListTheme {
        SearchBar(value = "", placeholder = "Search for movies")
    }
}