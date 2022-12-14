package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_items.view.*
import org.w3c.dom.Text

class Adapter(val context: Context, val newsList: List<DataItem>): RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(newsView: View): RecyclerView.ViewHolder(newsView) {
        var img_headline: ImageView
        var text_title: TextView
        var text_source: TextView
       init {
            text_title = newsView.text_title
            text_source = newsView.text_source
           img_headline = newsView.img_headline
        }
    }
    //3 funções geradas automaticamente quando introduzido os parametros na classe
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var newsView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(newsView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text_title.text = newsList[position].title
        holder.text_source.text = newsList[position].description
        Picasso.get().load(newsList[position].urlToImage).into(holder.img_headline)

    }
    override fun getItemCount(): Int {
        return newsList.size
    }
}