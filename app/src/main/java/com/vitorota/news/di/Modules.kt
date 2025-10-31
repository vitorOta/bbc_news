package com.vitorota.news.di

import com.vitorota.features.news.di.prepareNewsModule
import com.vitorota.news.BuildConfig
import org.koin.dsl.module

val appModule = module {
    includes(prepareNewsModule(country = BuildConfig.COUNTRY, provider = BuildConfig.PROVIDER))
}