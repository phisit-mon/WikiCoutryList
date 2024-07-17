package com.phisit.wikicoutrylist.utils

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

object WebViewUtil {

    val stayInAppWebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?, request: WebResourceRequest?
        ) = false
    }
}