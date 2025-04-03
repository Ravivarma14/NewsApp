package com.example.newsapp.ApiService

import com.example.newsapp.data.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country:String,
        @Query("apiKey") api:String
    ): NewsResponse
}