package com.example.task_5_news_feed.adapters

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_5_news_feed.entities.FeedItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class FeedAdapter(activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<FeedItem>? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private val delegatesManager = AdapterDelegatesManager<List<FeedItem>>()

    init {
        delegatesManager
            .addDelegate(NewsAdapterDelegate(activity))
            .addDelegate(NotificationsAdapterDelegate(activity))
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(items.orEmpty(), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return items?.size?:0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(items.orEmpty(), position, holder)
    }
}
