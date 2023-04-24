package com.universe.translator

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ImageButton
import androidx.appcompat.app.WindowDecorActionBar
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class SplashScreen : AppCompatActivity() {
    lateinit var viewpager:ViewPager2
    lateinit var indicator:DotsIndicator
    lateinit var imgbutton:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        viewpager=findViewById(R.id.pager2)
        indicator=findViewById(R.id.dot1)
        imgbutton=findViewById(R.id.imageButton)
        supportActionBar?.hide()
        inflateView()
        imgbutton.setOnClickListener {
            NaviagatetoMain()
        }



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

    fun NaviagatetoMain(){
        val intent=Intent(this, Translation_Screen::class.java)
        startActivity(intent)
        finish()
    }
}