package com.vitorota.features.news.data.remote.interceptor

import com.vitorota.features.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ParametersInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url.newBuilder()
            .addQueryParameter("provider", BuildConfig.PROVIDER)
            .addQueryParameter("country", BuildConfig.COUNTRY)
            .build()

        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}