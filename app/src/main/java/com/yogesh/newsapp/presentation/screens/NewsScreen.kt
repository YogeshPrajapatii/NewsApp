package com.yogesh.newsapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yogesh.newsapp.data.remote.dto.Article
import com.yogesh.newsapp.presentation.components.NewsList
import com.yogesh.newsapp.presentation.viewmodel.NewsViewModel
import com.yogesh.newsapp.util.NewsResult

/**
 * The main screen that displays the news feed.
 * It observes the UI state from the [NewsViewModel] and renders the appropriate UI.
 *
 * @param viewModel The ViewModel responsible for business logic and data fetching.
 * @param onArticleClick A callback invoked when a user clicks on a news article, used to trigger navigation.
 */
@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    onArticleClick: (Article) -> Unit
) {
    // Scaffold provides the basic Material Design layout structure.
    // It automatically handles system bars padding when `enableEdgeToEdge` is used in the Activity.
    Scaffold { innerPadding ->
        Box(
            // Apply the padding provided by Scaffold to prevent content from drawing under the status bar.
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val newsState = viewModel.newsState.collectAsState()

            // LaunchedEffect ensures the API call is made only once when the screen is first displayed.
            LaunchedEffect(key1 = Unit) {
                viewModel.getNews()
            }

            // This is State-Driven UI: The UI changes based on the value of `newsState`.
            when (val result = newsState.value) {
                is NewsResult.Loading -> CircularProgressIndicator()
                is NewsResult.Error -> Text(text = result.message ?: "An unknown error occurred.")
                is NewsResult.Success -> {
                    NewsList(
                        news = result.data,
                        onItemClick = onArticleClick
                    )
                }
                is NewsResult.Idle -> { /* Do nothing while in the initial idle state. */ }
            }
        }
    }
}