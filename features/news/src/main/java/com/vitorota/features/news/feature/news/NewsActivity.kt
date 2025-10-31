package com.vitorota.features.news.feature.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vitorota.features.news.feature.news.navigation.NewsDestinations
import com.vitorota.features.news.feature.news.screens.ArticleScreen
import com.vitorota.features.news.feature.news.screens.NewsListScreen

class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = Color.White) {
                    NewsFeaturesNavGraph()
                }
            }
        }
    }
}

@Composable
fun NewsFeaturesNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NewsDestinations.LIST_ROUTE
    ) {
        composable(route = NewsDestinations.LIST_ROUTE) {
            NewsListScreen(onArticleClick = {
                navController.navigate(NewsDestinations.ARTICLE_ROUTE)
            })
        }
        composable(route = NewsDestinations.ARTICLE_ROUTE) { backStackEntry ->
            ArticleScreen(onNavigateUp = {
                navController.navigateUp()
            })
        }
    }
}