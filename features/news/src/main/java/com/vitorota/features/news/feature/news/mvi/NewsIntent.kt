package com.vitorota.features.news.feature.news.mvi

import com.vitorota.features.news.domain.model.Article

sealed class NewsIntent {
    object FetchNews : NewsIntent()
    data class SelectArticle(val article: Article) : NewsIntent()

    object NavigateUp : NewsIntent()
}