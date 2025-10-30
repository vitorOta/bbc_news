package com.vitorota.features.news.feature.news.mvi

import com.vitorota.features.news.BuildConfig
import com.vitorota.features.news.domain.model.Article

sealed class NewsViewState {
    val title: String = BuildConfig.PROVIDER

    object InitialState : NewsViewState()
    data class ListContent(
        val articles: List<Article>
    ) : NewsViewState()

    data class ArticleContent(val article: Article) : NewsViewState()

    object Error : NewsViewState()
    object Loading : NewsViewState()
}
