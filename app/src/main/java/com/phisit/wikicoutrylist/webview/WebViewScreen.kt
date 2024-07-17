package com.phisit.wikicoutrylist.webview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.phisit.wikicoutrylist.utils.WebViewUtil

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WikiWebViewScreen(
    country: String,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.loadWithOverviewMode = true
                    this.settings.javaScriptEnabled = true
                    this.settings.loadWithOverviewMode = true
                    this.settings.useWideViewPort = true
                    this.settings.allowContentAccess = true
                    this.settings.allowFileAccess = true
                    this.settings.domStorageEnabled = true
                    this.webViewClient = WebViewUtil.stayInAppWebViewClient
                    webChromeClient = WebChromeClient()
                }
            }, update = { webView ->
                    webView.loadUrl("https://en.wikipedia.org/wiki/${country}")
            })
    }
}