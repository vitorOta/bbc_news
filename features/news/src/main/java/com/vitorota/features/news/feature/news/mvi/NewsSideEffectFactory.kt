package com.vitorota.features.news.feature.news.mvi

import com.vitorota.mvi.SideEffectFactory

class NewsSideEffectFactory : SideEffectFactory<NewsResult, NewsSideEffect> {
    override fun produce(result: NewsResult): NewsSideEffect? {
        return when (result) {
            is NewsResult.ArticleSelected -> NewsSideEffect.GoToArticle
            is NewsResult.NavigateUp -> NewsSideEffect.NavigateUp
            else -> null
        }
    }
}