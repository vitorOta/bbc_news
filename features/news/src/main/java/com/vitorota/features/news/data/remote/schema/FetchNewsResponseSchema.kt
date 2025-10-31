package com.vitorota.features.news.data.remote.schema

import com.google.gson.annotations.SerializedName

data class FetchNewsResponseSchema(
    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<ArticleSchema>
)