package com.example.task_5_news_feed.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task_5_news_feed.R
import com.example.task_5_news_feed.entities.FeedItem
import com.example.task_5_news_feed.entities.FeedType
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.notifications_layout.view.*

class NotificationsAdapterDelegate(val activity: Activity) : AdapterDelegate<List<FeedItem>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NotificationsViewHolder(activity.layoutInflater.inflate(R.layout.notifications_layout, parent, false))
    }

    override fun isForViewType(items: List<FeedItem>, position: Int): Boolean {
        return items[position].type == FeedType.Notification
    }

    override fun onBindViewHolder(
        items: List<FeedItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val notification = items[position]
        val vh = holder as NotificationsViewHolder
        Picasso.get().load(notification.imageUrl).into(vh.image)
        vh.title.text = notification.title
        vh.content.text = notification.content
    }

    inner class NotificationsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: CircleImageView = view.notification_image
        val title: TextView = view.notification_title
        val content: TextView = view.notification_content
    }
}
