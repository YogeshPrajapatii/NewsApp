package com.yogesh.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yogesh.newsapp.data.apiservice.NewsApiService
import com.yogesh.newsapp.data.repoimp.NewsRepoImp
import com.yogesh.newsapp.presentation.viewmodel.NewsViewModel
import com.yogesh.newsapp.presentation.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val apiKey = "536cd6fc9bf243b78991e9a398c49a77"
        val apiService = NewsApiService(apiKey)
        val newsRepo = NewsRepoImp(apiService)
        val viewModel = NewsViewModel(newsRepo)
        
        setContent {
            AppNavigation(viewModel = viewModel)
        }
    }
}

