package com.vitorota.features.news.di

import com.vitorota.features.news.data.NewsRepository
import com.vitorota.features.news.data.remote.RemoteNewsRepository
import com.vitorota.libraries.network.ApiBuilder
import org.koin.dsl.module

val newsModule = module {
    single<ApiBuilder> { ApiBuilder() }
    single<NewsRepository> { RemoteNewsRepository(get()) }
}