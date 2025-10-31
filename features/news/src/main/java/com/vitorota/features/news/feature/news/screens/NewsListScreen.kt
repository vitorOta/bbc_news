package com.vitorota.features.news.feature.news.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    //TODO add the lauched effect here to request the fingerprint
    LaunchedEffect(Unit) {
        viewModel.dispatch(NewsIntent.FetchNews)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = viewState) {
            is NewsViewState.Loading, is NewsViewState.ArticleContent -> {
                NewsLoading()
            }

            is NewsViewState.ListContent -> {
                NewsList(state.articles, onArticleClick, Modifier.fillMaxSize())
            }

            is NewsViewState.Error -> {
                NewsErrorText("${state.error.message} \n click to retry", Modifier.clickable {
                    viewModel.dispatch(NewsIntent.FetchNews)
                })
            }

            else -> {
                NewsErrorText("viewState not handled: $state")
            }
        }
    }
}

@Composable
private fun NewsList(
    articles: List<Article>,
    onArticleClick: (article: Article) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(articles) { article ->
            Text(
                text = article.title,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .clickable { onArticleClick(article) }
                    .padding(16.dp)
            )
        }
    }
}