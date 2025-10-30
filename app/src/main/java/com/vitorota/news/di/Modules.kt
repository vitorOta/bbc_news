package com.vitorota.news.di

import com.vitorota.features.news.di.newsModule
import org.koin.dsl.module

val appModule = module {
    includes(newsModule)
}