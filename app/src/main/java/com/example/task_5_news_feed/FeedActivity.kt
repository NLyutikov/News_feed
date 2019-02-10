package com.example.task_5_news_feed

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.Toolbar
import com.example.task_5_news_feed.adapters.FeedAdapter
import com.example.task_5_news_feed.entities.FeedItem
import com.example.task_5_news_feed.entities.FeedType
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    private val feedAdapter = FeedAdapter(this)

    private val items = initList()
    private val mOnNavigationItemSelectedListener = BottomNavigationView
        .OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_feed -> {
                feedAdapter.items = items
                cTLayout?.title = getString(R.string.title_feed)
                Picasso.get().load(getString(R.string.feed_img))
                    .error(R.drawable.errorjpg).into(feed_logo)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_news -> {
                filter(FeedType.News)
                cTLayout?.title = getString(R.string.title_news)
                Picasso.get().load(getString(R.string.news_img))
                    .error(R.drawable.errorjpg).into(feed_logo)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                filter(FeedType.Notification)
                cTLayout?.title = getString(R.string.title_notifications)
                Picasso.get().load(getString(R.string.notification_img))
                    .error(R.drawable.errorjpg).into(feed_logo)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private var cTLayout: CollapsingToolbarLayout? = null

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        cTLayout = findViewById(R.id.ct_layout)
        cTLayout!!.title = getString(R.string.title_feed)
        Picasso.get().load(getString(R.string.feed_img))
            .error(R.drawable.errorjpg).into(feed_logo)

        list.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = feedAdapter
        }
        feedAdapter.items = initList()

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun filter(type: FeedType) {
        feedAdapter.items = when (type) {
            FeedType.News -> items.filter { it.type == FeedType.News }
            FeedType.Notification -> items.filter { it.type == FeedType.Notification }
        }
    }

    private fun initList(): List<FeedItem> {
        return List(20) { index ->
            val isNews = index % 3 != 0
            FeedItem(
                id = index.toString(),
                title = if (isNews) "Новость $index" else "Уведомление $index",
                content = "Описание описанного в том описании, которое описал тот самый.",
                imageUrl = if (isNews) "https://i.ytimg.com/vi/56ClQNwzVIs/maxresdefault.jpg"
                else "https://encrypted-tbn0.gstatic.com" +
                        "/images?q=tbn:ANd9GcTGQ5wFkfrNtxdTbCsm0Crgae8dqKS9HtpwzPqJ3D8Z85mtUPrIDw",
                type = if (isNews) FeedType.News else FeedType.Notification,
                isLiked = false
            )
        }
    }
}
