package com.vitorota.libraries.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder {
    private var url = ""
    private val gsonBuilder = GsonBuilder().setDateFormat("yyyy-MM-ddTHH:mm:ssZ")
    private val client = OkHttpClient.Builder().build()

    fun url(url: String) = this.apply {
        this.url = url
    }

    fun dateFormat(dateFormat: String) = this.apply {
        this.gsonBuilder.setDateFormat(dateFormat)
    }

    fun <T> create(apiInterface: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(this.url)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(client)
            .build()

        return retrofit.create(apiInterface)
    }

}