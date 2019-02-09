package com.example.task_5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.support.v7.widget.Toolbar
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

        //dynamicToolBarColor()
        //toolbarTextAppearance()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

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
