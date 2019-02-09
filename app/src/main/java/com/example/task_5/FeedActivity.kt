package com.example.task_5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.Toolbar
import com.example.task_5.entities.FeedItem
import com.example.task_5.entities.FeedType
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    private val feedAdapter = FeedAdapter(this)

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_feed -> {
                feedAdapter.items = initList()
                cTLayout?.title = "Feed"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_news -> {
                filter(FeedType.News)
                cTLayout?.title = "News"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                filter(FeedType.Notification)
                cTLayout?.title = "Notifications"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    var cTLayout: CollapsingToolbarLayout? = null

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        cTLayout = findViewById(R.id.ct_layout)
        cTLayout!!.title = "Feed"

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
            FeedType.News -> initList().filter { it.type == FeedType.News }
            FeedType.Notification -> initList().filter { it.type == FeedType.Notification }
        }
    }

    private fun initList(): List<FeedItem> {
        return List(20) { index ->
            val isNews = index % 3 != 0
            FeedItem(
                id = index.toString(),
                title = if (isNews) "Новость $index" else "Уведомление $index",
                content = "Описание описанного в том описании, которое описал тот самый",
                imageUrl = "https://i.ytimg.com/vi/aVeCYjAiQHo/maxresdefault.jpg",
                type = if (isNews) FeedType.News else FeedType.Notification
            )
        }
    }

    private fun dynamicToolBarColor() {
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.feed_logo)
        androidx.palette.graphics.Palette.from(bitmap).generate {
            cTLayout!!.setContentScrimColor(it!!.getMutedColor(R.attr.color))
            cTLayout!!.setStatusBarScrimColor(it.getMutedColor(R.attr.color))
        }
    }

    private fun toolbarTextAppearance() {
        cTLayout!!.setCollapsedTitleTextAppearance(R.style.colAppBar)
        cTLayout!!.setExpandedTitleTextAppearance(R.style.expAppBar)
    }
}
