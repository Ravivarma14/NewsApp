package com.example.newsapp.Repository

import android.util.Log
import com.example.newsapp.ApiService.RetrofitInstance
import com.example.newsapp.data.Article

class NewsRepository {

    suspend fun getTopHeadlines(country:String, apiKey:String): List<Article>?{
        Log.d("TAG", "getTopHeadlines: country: "+ country+ " apiKey: "+ apiKey)
        return try{
            val responce= RetrofitInstance.api.getTopHeadlines(country,apiKey)
            Log.d("TAG", "getTopHeadlines: response: "+ responce.toString())
            if(responce.status.equals("ok")) responce.articles else null
        }
        catch (e: Exception){
            Log.d("TAG", "getTopHeadlines: Exception: "+ e)
            e.printStackTrace()
            null
        }
    }
}