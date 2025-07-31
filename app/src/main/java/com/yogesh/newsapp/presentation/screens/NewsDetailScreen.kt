package com.yogesh.newsapp.presentation.screens

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

/**
 * Displays the full news article in a WebView.
 * It shows a loading indicator while the page is loading and provides a back button.
 *
 * @param url The URL of the news article to display.
 * @param navController The NavController used to navigate back to the previous screen.
 */
@Composable
fun NewsDetailScreen(
    url: String,
    navController: NavController
) {
    // A state to track whether the WebView is currently loading a page.
    var isLoading by remember { mutableStateOf(true) }

    // Box is used to overlay the loading indicator and button on top of the WebView.
    Box(modifier = Modifier.fillMaxSize()) {
        // AndroidView is a bridge to use classic Android Views (like WebView) in Jetpack Compose.
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    // JavaScript is enabled as many modern websites require it to render correctly.
                    settings.javaScriptEnabled = true
                    // A custom WebViewClient manages page loading events.
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            isLoading = true
                        }
                        override fun onPageFinished(view: WebView?, url: String?) {
                            isLoading = false
                        }
                    }
                    loadUrl(url)
                }
            }
        )

        // Show a progress indicator only when the page is loading.
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        // A FloatingActionButton to provide a clear way to navigate back.
        FloatingActionButton(
            onClick = {
                // popBackStack navigates to the previous destination in the navigation graph.
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
    }
}