package com.example.newsapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.Repository.NewsRepository

class ViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}