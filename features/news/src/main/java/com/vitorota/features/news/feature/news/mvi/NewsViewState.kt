package com.vitorota.features.news.feature.news.mvi

import com.vitorota.features.news.BuildConfig
import com.vitorota.features.news.domain.model.Article

sealed class NewsViewState {
    data class ListContent(
        val articles: List<Article>
    ) : NewsViewState()

    data class ArticleContent(val article: Article) : NewsViewState()

    data class Error(val error: Throwable) : NewsViewState()
    object Loading : NewsViewState()
}
