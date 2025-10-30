package com.vitorota.features.news.feature.news

import com.vitorota.features.news.feature.news.mvi.NewsIntent
import com.vitorota.features.news.feature.news.mvi.NewsResult
import com.vitorota.features.news.feature.news.mvi.NewsResultFactory
import com.vitorota.features.news.feature.news.mvi.NewsViewState
import com.vitorota.features.news.feature.news.mvi.NewsViewStateFactory
import com.vitorota.mvi.MviViewModel

class NewsViewModel(
    resultFactory: NewsResultFactory,
    viewStateFactory: NewsViewStateFactory
) : MviViewModel<NewsIntent, NewsResult, NewsViewState>(
    initialViewState = NewsViewState.InitialState,
    resultFactory = resultFactory,
    viewStateFactory = viewStateFactory
)