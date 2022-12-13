package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity

import android.widget.TextView
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    var headlines: DataItem? = null
    var txt_title: TextView? = null
    var txt_author: TextView? = null
    var txt_time: TextView? = null
    var txt_detail: TextView? = null
    var text_content: TextView? = null
    var img_news: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        txt_title = findViewById(R.id.text_detail_title)
        txt_author = findViewById(R.id.text_detail_author)
        txt_time = findViewById(R.id.text_detail_time)
        txt_detail = findViewById(R.id.text_detail_detail)
        text_content = findViewById(R.id.text_detail_content)
        img_news = findViewById(R.id.img_detail_news)
        headlines = intent.getSerializableExtra("data") as DataItem?
        txt_title?.run { setText(headlines!!.title) }
        txt_author?.run { setText(headlines!!.author) }
        txt_time?.run { setText(headlines!!.publishedAt) }
        txt_detail?.run { setText(headlines!!.description) }
        text_content?.run { setText(headlines!!.content) }
        Picasso.get().load(headlines!!.urlToImage).into(img_news)
    }
}