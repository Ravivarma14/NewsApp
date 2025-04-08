package com.example.newsapp.ApiService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {

    private const val BASE_URL = "https://newsapi.org/"
    private const val LIKES_URL = "https://cn-news-info-api.herokuapp.com/likes/"
    private const val COMMENTS_URL = "https://cn-news-info-api.herokuapp.com/comments/"

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

    val likesAPI: LikesCommentsService by lazy {
        Retrofit.Builder()
            .baseUrl(LIKES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(LikesCommentsService::class.java)
    }

    val commentsAPI: LikesCommentsService by lazy {
        Retrofit.Builder()
            .baseUrl(COMMENTS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(LikesCommentsService::class.java)
    }
}