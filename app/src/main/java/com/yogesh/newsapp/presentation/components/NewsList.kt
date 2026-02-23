package com.yogesh.newsapp.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.yogesh.newsapp.data.remote.dto.Article

/**
 * A composable that displays a scrollable list of news articles using [NewsItem]s.
 *
 * @param news The list of [Article]s to be displayed.
 * @param onItemClick A lambda that gets triggered when any [NewsItem] in the list is clicked.
 */
@Composable
fun NewsList(
    news: List<Article>,
    onItemClick: (Article) -> Unit
) {
    // LazyColumn is highly efficient for displaying long lists.
    // It only renders the items currently visible on the screen.
    LazyColumn {

        items(
            items = news,
            // Providing a unique key helps Compose optimize recompositions.
            key = { article -> article.url ?: article.title ?: "" }
        ) { article ->
            NewsItem(
                article = article,
                onItemClick = onItemClick
            )
        }
    }
}