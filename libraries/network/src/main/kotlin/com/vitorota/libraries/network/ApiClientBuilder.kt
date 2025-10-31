package com.vitorota.libraries.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder {
    private var url = ""
    private var gson = GsonBuilder().create()
    private val baseCient = OkHttpClient.Builder().build()

    private val myInterceptors = mutableListOf<Interceptor>()

    fun url(url: String) = this.apply {
        this.url = url
    }

    fun gson(gson: Gson) = this.apply {
        this.gson = gson
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
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        return retrofit.create(apiInterface)
    }

}