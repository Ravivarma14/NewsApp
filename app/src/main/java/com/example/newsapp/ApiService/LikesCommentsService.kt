package com.example.newsapp.ApiService

import com.example.newsapp.data.NewsResponse
import retrofit2.http.GET

interface LikesCommentsService {

    @GET("/likes/")
    suspend fun getLikesFor(
        url:String
    ): Int

    @GET("/comments/")
    suspend fun getComments(
        url:String
    ): Int
}