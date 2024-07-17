package com.phisit.wikicoutrylist.webview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.phisit.wikicoutrylist.ui.theme.WikiCoutryListTheme

class WikiWebViewActivity : ComponentActivity() {

    companion object {
        val COUNTRY_INTENT_EXTRA = "COUNTRY_INTENT_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val country = intent.getStringExtra(COUNTRY_INTENT_EXTRA).orEmpty()

        setContent {
            WikiCoutryListTheme {

                Scaffold(
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("toolbar_layout"),
                        ) {
                            Text(
                                text = country,
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                            IconButton(
                                modifier = Modifier.align(Alignment.CenterStart),
                                onClick = {
                                    finish()
                                }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "back",
                                )
                            }
                        }
                    },
                    bottomBar = {},
                    content = { paddingValues ->
                        WikiWebViewScreen(
                            country,
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                )
            }
        }
    }
}