package com.vitorota.features.news.feature.news.mvi

import com.vitorota.features.news.domain.model.Article

sealed class NewsResult {
    data class Success(val data: List<Article>) : NewsResult()
    object Error : NewsResult()
    object Loading : NewsResult()
}