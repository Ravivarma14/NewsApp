package com.example.newsapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.data.Article
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository): ViewModel() {
    private val _articles = MutableLiveData<List<Article>?>()
    val articles:LiveData<List<Article>?> get() = _articles

    fun fetchTopHeadlines(country:String, apiKey:String){
        viewModelScope.launch {
            val fetchedArticles=repository.getTopHeadlines(country,apiKey)
//            Log.d("TAG", "fetchTopHeadlines: articles: "+ fetchedArticles.toString())
            _articles.postValue(fetchedArticles)
        }
    }
}