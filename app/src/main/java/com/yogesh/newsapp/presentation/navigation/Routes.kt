package com.yogesh.newsapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object NewsScreen : Routes()

    @Serializable
    data class NewsDetailScreen(val url: String) : Routes()
}