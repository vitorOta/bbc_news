package com.vitorota.features.news.feature.news.mvi

import com.vitorota.features.news.data.NewsRepository
import com.vitorota.features.news.domain.model.Article
import com.vitorota.mvi.ResultFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class NewsResultFactory(private val newsRepository: NewsRepository) :
    ResultFactory<NewsIntent, NewsResult> {

    override fun process(intent: NewsIntent): Flow<NewsResult> {
        return when (intent) {
            is NewsIntent.FetchNews -> fetchArticles()
            is NewsIntent.SelectArticle -> selectArticle(intent.article)
            is NewsIntent.NavigateUp -> flow { NewsResult.NavigateUp }
        }
    }

    private fun fetchArticles() = flow<NewsResult> {
        emit(NewsResult.ListSuccess(newsRepository.fetchArticles()))
    }.onStart {
        emit(NewsResult.Loading)
    }.catch {
        emit(NewsResult.Error(it))
    }

    private fun selectArticle(article: Article) = flow<NewsResult> {
        emit(NewsResult.ArticleSelected(article))
    }
}
