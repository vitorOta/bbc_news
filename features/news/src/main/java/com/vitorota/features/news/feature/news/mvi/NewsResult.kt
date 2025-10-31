package com.vitorota.features.news.feature.news.mvi

import com.vitorota.features.news.domain.model.Article

sealed class NewsResult {
    data class ListSuccess(val data: List<Article>) : NewsResult()
    data class ArticleSelected(val data: Article) : NewsResult()
    data class Error(val error: Throwable) : NewsResult()
    object Loading : NewsResult()

    object NavigateUp : NewsResult()
}