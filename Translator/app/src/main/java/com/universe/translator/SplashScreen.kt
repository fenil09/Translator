package com.universe.translator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class SplashScreen : AppCompatActivity() {
    lateinit var viewpager:ViewPager2
    lateinit var indicator:DotsIndicator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        viewpager=findViewById(R.id.pager2)
        indicator=findViewById(R.id.dot1)
        inflateView()
    }

    fun inflateView(){
        viewpager.setBackgroundColor(Color.GREEN)
        val imglist= mutableListOf<Int>()
        imglist.add(R.drawable.tr1)
        imglist.add(R.drawable.tr2)
        imglist.add(R.drawable.tr3)
        imglist.add(R.drawable.tr4)
        viewpager.adapter=Pager(imglist, this@SplashScreen)
        viewpager.orientation=ViewPager2.ORIENTATION_HORIZONTAL
        indicator.attachTo(viewpager)
    }
}