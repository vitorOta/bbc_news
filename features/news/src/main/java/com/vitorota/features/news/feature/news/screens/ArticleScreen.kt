package com.vitorota.features.news.feature.news.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vitorota.features.news.domain.model.Article
import com.vitorota.features.news.feature.news.mvi.NewsViewModel
import com.vitorota.features.news.feature.news.mvi.NewsViewState
import com.vitorota.features.news.feature.news.screens.sharedviews.NewsErrorText
import com.vitorota.features.news.feature.news.screens.sharedviews.NewsLoading

@Composable
fun ArticleScreen(
    viewModel: NewsViewModel
) {
    val viewState by viewModel.viewState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = viewState) {
            is NewsViewState.Loading, is NewsViewState.ListContent -> {
                NewsLoading()
            }

            is NewsViewState.ArticleContent -> {
                ArticleDetail(state.article, Modifier.fillMaxSize())
            }

            is NewsViewState.Error -> {
                NewsErrorText(state.error.message ?: "unknown")
            }

            else -> {
                NewsErrorText("viewState not handled: $state")
            }
        }
    }
}

@Composable
fun ArticleDetail(article: Article, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.padding(8.dp)) {
        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {
            if (article.urlToImage.isNotBlank()) {
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = "Article image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
            Text(
                text = article.title,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            HorizontalDivider()

            Text(
                text = article.description,
                color = Color.Gray,
                fontSize = 12.sp,
            )
            HorizontalDivider()

            Text(
                text = article.content,
                fontSize = 14.sp
            )
        }
    }
}
