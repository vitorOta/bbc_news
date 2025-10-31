package com.vitorota.features.news.feature.news.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitorota.features.news.domain.model.Article
import com.vitorota.features.news.feature.news.mvi.NewsViewModel
import com.vitorota.features.news.feature.news.mvi.NewsViewState
import com.vitorota.features.news.feature.news.screens.sharedviews.NewsErrorText
import com.vitorota.features.news.feature.news.screens.sharedviews.NewsLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleScreen(
    viewModel: NewsViewModel = koinViewModel(),
    onNavigateUp: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = viewState) {
            is NewsViewState.Loading -> {
                NewsLoading()
            }
            is NewsViewState.ArticleContent -> {
                ArticleDetail(state.article, Modifier.fillMaxSize())
            }

            is NewsViewState.Error -> {
                Text(
                    text = "Error: ${state.error.message}\n",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                )
            }
            else -> {
                NewsErrorText("viewState not handled: $state")
            }
        }
    }
}

@Composable
fun ArticleDetail(article: Article, modifier: Modifier = Modifier) {
    Text(
        text = article.title,
        color = Color.Blue,
        fontSize = 20.sp,
        modifier = modifier.padding(20.dp)
    )
}
