package com.example.task_5

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task_5.entities.FeedItem
import com.example.task_5.entities.FeedType
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_layout.view.*

class NewsAdapterDelegate(val activity: Activity) : AdapterDelegate<List<FeedItem>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(activity.layoutInflater.inflate(R.layout.news_layout, parent, false))
    }

    override fun isForViewType(items: List<FeedItem>, position: Int): Boolean {
        return items[position].type == FeedType.News
    }

    override fun onBindViewHolder(
        items: List<FeedItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val news = items[position]
        val vh = holder as NewsViewHolder
        Picasso.get().load(news.imageUrl).into(vh.image)
        vh.title.text = news.title
        vh.content.text = news.content
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.news_image
        val title: TextView = view.news_title
        val content: TextView = view.news_content
    }
}