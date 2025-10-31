package com.vitorota.features.news.feature.news.mvi

import com.vitorota.mvi.MviViewModel
import com.vitorota.mvi.ResultFactory
import com.vitorota.mvi.ViewStateFactory

class NewsViewModel(
    resultFactory: ResultFactory<NewsIntent, NewsResult>,
    viewStateFactory: ViewStateFactory<NewsResult, NewsViewState>
) : MviViewModel<NewsIntent, NewsResult, NewsViewState>(
    initialViewState = NewsViewState.InitialState,
    defaultErrorViewState = NewsViewState.Error(RuntimeException("viewModel default exception")),
    resultFactory = resultFactory,
    viewStateFactory = viewStateFactory
)