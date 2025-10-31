package com.vitorota.features.news.data.remote

import com.vitorota.features.news.data.NewsRepository
import com.vitorota.features.news.data.remote.schema.ArticleSchema
import com.vitorota.features.news.domain.model.Article
import com.vitorota.libraries.network.ApiBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteNewsRepository(apiBuilder: ApiBuilder) : NewsRepository {
    private val api = apiBuilder
        .create(NewsApi::class.java)

    override suspend fun fetchArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            val response = api.getHeadlines()
            val articles = response.body()?.articles
            articles?.map { it.toArticle() } ?: throw RuntimeException("api error")
        }
    }
}

private fun ArticleSchema.toArticle() = Article(
    title = this.title,
    description = this.description,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content
)
