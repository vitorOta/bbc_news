package com.vitorota.features.news.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ParametersInterceptor(val country: String, val provider: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url.newBuilder()
            .apply {
                if (country.isNotBlank()) {
                    addQueryParameter("country", country)
                }
                if (provider.isNotBlank()) {
                    addQueryParameter("provider", provider)
                }
            }

            .build()

        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}