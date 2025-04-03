package com.example.newsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.Article

class NewsAdapter(private val onItemClicked: (Article) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private val articles= mutableListOf<Article>()

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.publishedTextView)
        private val newsImageView: ImageView = itemView.findViewById(R.id.newsImageView)

        fun bind(article: Article) {
            titleTextView.text = article.title
            descriptionTextView.text = article.publishedAt ?: ""
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder)
                .into(newsImageView)

            itemView.setOnClickListener { onItemClicked(article) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    fun updateArticles(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
}