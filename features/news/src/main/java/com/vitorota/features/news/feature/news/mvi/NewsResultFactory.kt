package com.vitorota.features.news.feature.news.mvi

import com.vitorota.features.news.data.NewsRepository
import com.vitorota.mvi.ResultFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class NewsResultFactory(private val newsRepository: NewsRepository) : ResultFactory<NewsIntent, NewsResult> {

    override fun process(intent: NewsIntent): Flow<NewsResult> {
        return when (intent) {
            NewsIntent.FetchNews -> fetchArticles()
        }
    }

    private fun fetchArticles() = flow<NewsResult> {
        emit(NewsResult.Success(newsRepository.fetchArticles()))
    }.onStart {
        emit(NewsResult.Loading)
    }.catch { emit(NewsResult.Error) }
}
