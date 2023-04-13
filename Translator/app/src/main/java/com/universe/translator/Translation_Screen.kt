package com.universe.translator

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar

class Translation_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation_screen)
        supportActionBar?.show()
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.themecolor)))
    }
}