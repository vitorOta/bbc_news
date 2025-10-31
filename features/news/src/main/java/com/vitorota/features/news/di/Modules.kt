package com.vitorota.features.news.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vitorota.features.news.BuildConfig
import com.vitorota.features.news.data.NewsRepository
import com.vitorota.features.news.data.remote.RemoteNewsRepository
import com.vitorota.features.news.data.remote.interceptor.AuthenticationInterceptor
import com.vitorota.features.news.data.remote.interceptor.ParametersInterceptor
import com.vitorota.features.news.feature.news.mvi.NewsIntent
import com.vitorota.features.news.feature.news.mvi.NewsResult
import com.vitorota.features.news.feature.news.mvi.NewsResultFactory
import com.vitorota.features.news.feature.news.mvi.NewsViewModel
import com.vitorota.features.news.feature.news.mvi.NewsViewState
import com.vitorota.features.news.feature.news.mvi.NewsViewStateFactory
import com.vitorota.libraries.network.ApiBuilder
import com.vitorota.mvi.ResultFactory
import com.vitorota.mvi.ViewStateFactory
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun prepareNewsModule(country: String, provider: String) = module {
    single<String>(named("country")) { country }
    single<String>(named("provider")) { provider }
    single<Gson> { configureGson() }
    single<ApiBuilder> {
        configureApiBuilder(
            gson = get(),
            country = get(named("country")),
            provider = get(named("provider"))
        )
    }
    single<NewsRepository> { RemoteNewsRepository(get()) }

    single<ResultFactory<NewsIntent, NewsResult>> { NewsResultFactory(get()) }
    single<ViewStateFactory<NewsResult, NewsViewState>> { NewsViewStateFactory() }

    viewModelOf(::NewsViewModel)
}

private fun configureGson() = GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    .create()

private fun configureApiBuilder(gson: Gson, country: String, provider: String) =
    ApiBuilder()
        .url(BuildConfig.DATA_URL)
        .gson(gson)
        .addInterceptor(AuthenticationInterceptor())
        .addInterceptor(ParametersInterceptor(country = country, provider = provider))

