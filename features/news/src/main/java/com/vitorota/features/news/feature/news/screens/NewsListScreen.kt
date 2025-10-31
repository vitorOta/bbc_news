package com.vitorota.features.news.feature.news.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vitorota.features.news.domain.model.Article
import com.vitorota.features.news.feature.news.mvi.NewsIntent
import com.vitorota.features.news.feature.news.mvi.NewsViewModel
import com.vitorota.features.news.feature.news.mvi.NewsViewState
import com.vitorota.features.news.feature.news.screens.sharedviews.NewsErrorText
import com.vitorota.features.news.feature.news.screens.sharedviews.NewsLoading

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel,
    onArticleClick: (Article) -> Unit
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

            is NewsViewState.ListContent -> {
                NewsList(state.articles, onArticleClick, Modifier.fillMaxSize())
            }

            is NewsViewState.Error -> {
                NewsErrorText("${state.error.message} \n click to retry", Modifier.clickable {
                    viewModel.startScreen()
                })
            }

            is NewsViewState.ArticleContent -> {
                NewsLoading()
            }

            else -> {
                NewsErrorText("viewState not handled: $state")
            }
        }
    }
}

private fun NewsViewModel.startScreen() {
    dispatch(NewsIntent.FetchNews)
}

@Composable
private fun NewsList(
    articles: List<Article>,
    onArticleClick: (article: Article) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(articles) { article ->
            ArticleItem(article, onArticleClick, modifier = Modifier.fillParentMaxWidth())
        }
    }
}


@Composable
fun ArticleItem(article: Article, onClick: (Article) -> Unit, modifier: Modifier = Modifier) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(12.dp),
        onClick = { onClick(article) }
    ) {
        if (article.urlToImage.isNotBlank()) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "Article image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
        }
        Text(
            text = article.title,
            modifier = Modifier.padding(12.dp)
        )
    }
}