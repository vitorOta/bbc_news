package com.vitorota.features.news.data.remote

import com.vitorota.features.news.data.remote.schema.FetchNewsResponseSchema
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getHeadlines(): Response<FetchNewsResponseSchema>
}