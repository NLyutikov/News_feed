package com.example.task_5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.DrawableCompat
import com.example.task_5.entities.FeedItem
import com.example.task_5.entities.FeedType
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    private val feedAdapter = FeedAdapter(this)

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_feed -> {
                feedAdapter.items = initList()
                cTLayout?.title = "Feed"
                Picasso.get().load("https://images.wallpaperscraft.com/image/ice_cube_light_glitter_15376_1920x1200.jpg").error(R.drawable.errorjpg).into(feed_logo)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_news -> {
                filter(FeedType.News)
                cTLayout?.title = "News"
                Picasso.get().load("http://bgfons.com/uploads/pattern/pattern_texture1157.jpg").error(R.drawable.errorjpg).into(feed_logo)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                filter(FeedType.Notification)
                cTLayout?.title = "Notifications"
                Picasso.get().load("https://www.zastavki.com/pictures/originals/2014/Nature___Seasons___Spring_Walk_under_the_spring_sun_069264_.jpg").error(R.drawable.errorjpg).into(feed_logo)
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
        Picasso.get().load("https://images.wallpaperscraft.com/image/ice_cube_light_glitter_15376_1920x1200.jpg").error(R.drawable.errorjpg).into(feed_logo)

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
                imageUrl = if (isNews) "https://i.ytimg.com/vi/56ClQNwzVIs/maxresdefault.jpg" else "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGQ5wFkfrNtxdTbCsm0Crgae8dqKS9HtpwzPqJ3D8Z85mtUPrIDw",
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
