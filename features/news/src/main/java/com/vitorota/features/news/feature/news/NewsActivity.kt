package com.vitorota.features.news.feature.news

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
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
import com.vitorota.features.news.requestBiometry
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    Scaffold(
//        topBar = { TopAppBar(title = { Text("") }) },
        content = { innerPadding ->
            Surface(color = Color.White, modifier = Modifier.padding(innerPadding)) {
                NewsFeaturesNavGraph(koinViewModel())
            }
        }
    )

}

@Composable
fun NewsFeaturesNavGraph(viewModel: NewsViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        launch {
            viewModel.sideEffect.collect { sideEffect ->
                handleSideEffect(viewModel, sideEffect, navController, context as FragmentActivity)
            }
        }

        viewModel.dispatch(NewsIntent.RequestBiometry)
    }

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
}

private fun handleSideEffect(
    viewModel: NewsViewModel,
    sideEffect: NewsSideEffect,
    navController: NavHostController,
    activity: FragmentActivity
) {
    when (sideEffect) {
        is NewsSideEffect.NavigateUp -> {
            navController.navigateUp()
        }

        is NewsSideEffect.GoToArticle -> {
            navController.navigate(NewsDestinations.ARTICLE_ROUTE)
        }

        is NewsSideEffect.PromptBiometry -> {
            requestBiometry(
                activity,
                {
                    viewModel.dispatch(NewsIntent.FetchNews)
                },
                {
                    viewModel.dispatch(NewsIntent.FetchNews)
                })
        }
    }
}
