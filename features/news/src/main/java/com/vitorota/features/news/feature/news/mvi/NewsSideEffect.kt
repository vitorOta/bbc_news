package com.vitorota.features.news.feature.news.mvi

sealed class NewsSideEffect {
    object NavigateUp : NewsSideEffect()
    object GoToArticle : NewsSideEffect()
    object PromptBiometry : NewsSideEffect()
}