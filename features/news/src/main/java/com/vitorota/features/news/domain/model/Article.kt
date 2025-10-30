package com.vitorota.features.news.domain.model

import java.util.Date

data class Article(
    val title:String,
    val description: String,
    val urlToImage:String,
    val publishedAt: Date,
    val content:String
)