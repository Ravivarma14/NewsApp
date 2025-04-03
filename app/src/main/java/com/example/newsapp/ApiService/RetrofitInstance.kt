package com.example.newsapp.ApiService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {

    private const val BASE_URL = "https://newsapi.org/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY  // Logs request and response
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    val api: NetworkApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(NetworkApiService::class.java)
    }
}