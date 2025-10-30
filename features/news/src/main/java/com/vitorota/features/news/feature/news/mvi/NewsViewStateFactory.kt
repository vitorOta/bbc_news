package com.vitorota.features.news.feature.news.mvi

import com.vitorota.mvi.ViewStateFactory

class NewsViewStateFactory : ViewStateFactory<NewsResult, NewsViewState> {
    override fun reduce(result: NewsResult, previousViewState: NewsViewState): NewsViewState {
        return when (result) {
            is NewsResult.Success -> NewsViewState.ListContent(result.data)
            NewsResult.Loading -> NewsViewState.Loading
            is NewsResult.Error -> NewsViewState.Error
        }
    }
}