package com.yogesh.newsapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yogesh.newsapp.R
import com.yogesh.newsapp.data.remote.dto.Article

/**
 * A composable that displays a single news article in a card layout.
 * It handles image loading, placeholder, and error states gracefully.
 *
 * @param article The [Article] data to display.
 * @param onItemClick A lambda function that is invoked when the card is clicked.
 */
@Composable
fun NewsItem(
    article: Article,
    onItemClick: (Article) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemClick(article) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // This Box acts as a container for the image, allowing us to overlay UI elements
            // and handle different loading states.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                // This state tracks if the image has failed to load.
                // It's keyed to the image URL, so it resets for each new item in the list.
                var isError by remember(article.urlToImage) { mutableStateOf(false) }

                if (isError) {
                    // If an error occurs, display a fallback icon.
                    Icon(
                        painter = painterResource(id = R.drawable.ic_image_error),
                        contentDescription = "Error Icon",
                        // The icon is given a fixed size to prevent it from stretching.
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    )
                } else {
                    // If there's no error, attempt to load the image using Coil's AsyncImage.
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = article.title,
                        // ContentScale.Crop scales the image to fill the container, cropping excess parts.
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        // The onError callback updates our state if image loading fails.
                        onError = { isError = true }
                    )
                }
            }

            // This Column holds the text content of the news article.
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = article.source?.name ?: "Unknown Source",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = article.title ?: "No Title",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
            }
        }
    }
}