package com.vitorota.libraries.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder {
    private var url = ""
    private val gsonBuilder = GsonBuilder().setDateFormat("yyyy-MM-ddTHH:mm:ssZ")
    private val baseCient = OkHttpClient.Builder().build()

    private val myInterceptors = mutableListOf<Interceptor>()

    fun url(url: String) = this.apply {
        this.url = url
    }

    fun dateFormat(dateFormat: String) = this.apply {
        this.gsonBuilder.setDateFormat(dateFormat)
    }

    fun addInterceptor(interceptor: Interceptor) = this.apply {
        this.myInterceptors += interceptor
    }

    fun <T> create(apiInterface: Class<T>): T {
        val client = baseCient.newBuilder()
            .apply {
                myInterceptors.forEach { addInterceptor(it) }
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(this.url)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(client)
            .build()

        return retrofit.create(apiInterface)
    }

}