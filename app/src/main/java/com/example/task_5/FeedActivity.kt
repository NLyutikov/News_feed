package com.example.task_5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.support.v7.widget.Toolbar
import com.example.task_5.entities.FeedItem
import com.example.task_5.entities.FeedType
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_feed -> {
                //message.setText(R.string.title_feed)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_news -> {
                //message.setText(R.string.title_news)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //message.setText(R.string.title_notifications)
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

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initList(): List<FeedItem> {
        return List(20) { index ->
            val isNews = index % 3 != 0
            FeedItem(
                id = index.toString(),
                title = if (isNews) "Новость $index" else "Уведомление $index",
                description = "Описание описанного в том описании, которое описал тот самый",
                imageUrl = "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjl4deKiK_gAhUPpYsKHRqpABUQjRx6BAgBEAU&url=https%3A%2F%2Fmemepedia.ru%2Fstrana-imeni-rikardo-milosa-striptizer-iz-memov-zapolonil-google-maps%2F&psig=AOvVaw2hY7ElzF-z9COH2vA2XdlE&ust=1549815840803754",
                type = if (isNews) FeedType.News else FeedType.Notification
            )
        }
    }

    private fun dynamicToolBarColor() {
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.feed_logo)
        Palette.from(bitmap).generate {
            cTLayout!!.setContentScrimColor(it!!.getMutedColor(R.attr.color))
            cTLayout!!.setStatusBarScrimColor(it.getMutedColor(R.attr.color))
        }
    }

    private fun toolbarTextAppearance() {
        cTLayout!!.setCollapsedTitleTextAppearance(R.style.colAppBar)
        cTLayout!!.setExpandedTitleTextAppearance(R.style.expAppBar)
    }
}
