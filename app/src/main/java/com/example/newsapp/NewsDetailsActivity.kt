package com.example.newsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class NewsDetailsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_details)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

    }

    override fun onResume() {
        super.onResume()


        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("imageUrl")
        val url = intent.getStringExtra("url")
        val content = intent.getStringExtra("content")
        val publishedAt = intent.getStringExtra("published")

        findViewById<TextView>(R.id.detailTitleTextView).text = title
        findViewById<TextView>(R.id.detailDescriptionTextView).text = description
        findViewById<TextView>(R.id.detailContentTextView).text = content
        findViewById<TextView>(R.id.detailPublishedAtTextView).text = publishedAt

        if(imageUrl!=null && !imageUrl.isEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .into(findViewById(R.id.detailImageView))
        }
        else{
            findViewById<ImageView>(R.id.detailImageView).setImageDrawable(getDrawable(R.drawable.placeholder))
        }

        findViewById<Button>(R.id.readMoreButton).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }
}