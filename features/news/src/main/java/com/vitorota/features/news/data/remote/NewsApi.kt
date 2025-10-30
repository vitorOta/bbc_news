package com.vitorota.features.news.data.remote

import com.vitorota.features.news.data.remote.schema.ArticleSchema
import retrofit2.http.GET

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getHeadlines(): List<ArticleSchema>
}