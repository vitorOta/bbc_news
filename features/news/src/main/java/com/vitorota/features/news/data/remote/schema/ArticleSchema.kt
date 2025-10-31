package com.vitorota.features.news.data.remote.schema

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ArticleSchema(
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: Date?,
    @SerializedName("content")
    val content: String?
)