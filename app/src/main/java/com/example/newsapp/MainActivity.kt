package com.example.newsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Adapter.NewsAdapter
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.util.Constants.API_KEY
import com.example.newsapp.viewModels.NewsViewModel
import com.example.newsapp.viewModels.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onResume() {
        super.onResume()

        val progressBar:ProgressBar = findViewById(R.id.progress_bar)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility= View.VISIBLE
        recyclerView.visibility= View.GONE

        newsAdapter = NewsAdapter { article ->
            val intent = Intent(this, NewsDetailsActivity::class.java)
            intent.putExtra("title", article.title)
            intent.putExtra("description", article.description)
            intent.putExtra("imageUrl", article.urlToImage)
            intent.putExtra("url", article.url)
            intent.putExtra("content",article.content)
            intent.putExtra("published",article.publishedAt)
            startActivity(intent)
        }
        recyclerView.adapter = newsAdapter

        val repository = NewsRepository()
        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[NewsViewModel::class.java]

        viewModel.articles.observe(this) { articles ->

            if(progressBar.visibility == View.VISIBLE) progressBar.visibility= View.GONE
            if(recyclerView.visibility == View.GONE) recyclerView.visibility= View.VISIBLE
            articles?.let { newsAdapter.updateArticles(it) }
        }

        viewModel.fetchTopHeadlines("us", API_KEY)
    }
}