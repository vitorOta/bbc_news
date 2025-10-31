package com.vitorota.features.news.feature.news.mvi

import com.vitorota.mvi.ViewStateFactory

class NewsViewStateFactory : ViewStateFactory<NewsResult, NewsViewState> {
    override fun reduce(result: NewsResult, previousViewState: NewsViewState): NewsViewState {
        return when (result) {
            is NewsResult.ListSuccess -> NewsViewState.ListContent(result.data)
            is NewsResult.ArticleSelected -> NewsViewState.ArticleContent(result.data)
            is NewsResult.Loading -> NewsViewState.Loading
            is NewsResult.Error -> NewsViewState.Error(result.error)
        }
    }
}