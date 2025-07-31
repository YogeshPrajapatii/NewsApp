package com.yogesh.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.yogesh.newsapp.data.remote.dto.Article
import com.yogesh.newsapp.presentation.viewmodel.NewsViewModel
import com.yogesh.newsapp.presentation.screens.NewsDetailScreen
import com.yogesh.newsapp.presentation.screens.NewsScreen
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun AppNavigation(viewModel: NewsViewModel) {
    // NavController yahan banaya gaya hai. Ye is function ka local variable hai.
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.NewsScreen) {

        // Pehli screen: NewsScreen
        composable<Routes.NewsScreen> {
            NewsScreen(
                viewModel = viewModel,
                onArticleClick = { article: Article ->
                    val encodedUrl = URLEncoder.encode(article.url, "UTF-8")
                    // FIX: Ab navigate karte time hum sirf 'url' bhej rahe hain.
                    navController.navigate(Routes.NewsDetailScreen(url = encodedUrl))
                }
            )
        }

        // Doosri screen: NewsDetailScreen
        composable<Routes.NewsDetailScreen> { backStackEntry ->
            val routeObject = backStackEntry.toRoute<Routes.NewsDetailScreen>()
            val url = URLDecoder.decode(routeObject.url, "UTF-8")
            // FIX: Yahan hum 'NewsDetailScreen' ko local 'navController' pass kar rahe hain.
            NewsDetailScreen(url = url, navController = navController)
        }
    }
}