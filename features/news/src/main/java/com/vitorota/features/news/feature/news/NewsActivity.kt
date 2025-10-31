package com.vitorota.features.news.feature.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vitorota.features.news.feature.news.mvi.NewsIntent
import com.vitorota.features.news.feature.news.mvi.NewsSideEffect
import com.vitorota.features.news.feature.news.mvi.NewsViewModel
import com.vitorota.features.news.feature.news.navigation.NewsDestinations
import com.vitorota.features.news.feature.news.screens.ArticleScreen
import com.vitorota.features.news.feature.news.screens.NewsListScreen
import org.koin.androidx.compose.koinViewModel

class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = Color.White) {
                    NewsFeaturesNavGraph(koinViewModel())
                }
            }
        }
    }
}

@Composable
fun NewsFeaturesNavGraph(viewModel: NewsViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NewsDestinations.LIST_ROUTE
    ) {
        composable(route = NewsDestinations.LIST_ROUTE) {
            NewsListScreen(
                viewModel,
                onArticleClick = {
                    viewModel.dispatch(NewsIntent.SelectArticle(it))
                })
        }
        composable(route = NewsDestinations.ARTICLE_ROUTE) { backStackEntry ->
            ArticleScreen(viewModel)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            handleSideEffect(sideEffect, navController)
        }
    }
}

private fun handleSideEffect(sideEffect: NewsSideEffect, navController: NavHostController) {
    when (sideEffect) {
        is NewsSideEffect.NavigateUp -> {
            navController.navigateUp()
        }

        is NewsSideEffect.GoToArticle -> {
            navController.navigate(NewsDestinations.ARTICLE_ROUTE)
        }
    }
}