package com.universe.translator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {
    lateinit var input:EditText
    lateinit var translate:Button
    lateinit var view:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input=findViewById(R.id.et1)
        translate=findViewById(R.id.button)
        view=findViewById(R.id.textView)
        val gototextrecognizer:Button=findViewById(R.id.button4)
        translate.setOnClickListener {
            val inputtext=input.text.toString()
            translateText(inputtext)
        }
        gototextrecognizer.setOnClickListener {
            val intent=Intent(this,TextExtractor::class.java)
            startActivity(intent)
        }
    }
    fun translateText(text:String) {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()

        val englishspanishtranslator = Translation.getClient(options)

        englishspanishtranslator.downloadModelIfNeeded().addOnCompleteListener {
            if(it.isComplete){
                englishspanishtranslator.translate(text).addOnSuccessListener {
                    val translatedata=it.toString()
                    view.setText(translatedata)
                }
            }
            else{
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            }
        }
    }
}


