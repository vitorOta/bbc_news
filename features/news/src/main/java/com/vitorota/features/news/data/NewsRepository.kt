package com.vitorota.features.news.data

import com.vitorota.features.news.domain.model.Article

interface NewsRepository {
    suspend fun fetchArticles(): List<Article>
}