package com.universe.translator

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.google.mlkit.vision.text.TextRecognition

class TextExtractor : AppCompatActivity() {
    lateinit var view:ImageView
    lateinit var selectimage:Button
    lateinit var identifytext:Button
    lateinit var display:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_extractor)
        view=findViewById(R.id.imageView)
        selectimage=findViewById(R.id.button2)
        identifytext=findViewById(R.id.button3)
        display=findViewById(R.id.textView2)
        selectimage.setOnClickListener {
            getimage()
        }
        identifytext.setOnClickListener {
            recognizetext()
        }
    }

    fun getimage(){
        val intent=Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"selectimage",),1)
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1 && resultCode== Activity.RESULT_OK){
            if(data!=null){
                view.setImageURI(data.data)
            }
        }
    }

    fun recognizetext(){

        val bitmap=(view.drawable as BitmapDrawable).bitmap
        val image=FirebaseVisionImage.fromBitmap(bitmap)
        val recognize=FirebaseVision.getInstance().onDeviceTextRecognizer
        recognize.processImage(image).addOnSuccessListener {
            val resulttext=it.text
            translateText(resulttext)
        }.addOnFailureListener {
            Toast.makeText(this,"sorry could not recognize the text",Toast.LENGTH_LONG).show()
        }

    }


    fun translateText(text:String){
        val options=TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()

        val englishspanishtranslator=Translation.getClient(options)

        englishspanishtranslator.downloadModelIfNeeded().addOnCompleteListener {
            if(it.isComplete){
                englishspanishtranslator.translate(text).addOnSuccessListener {
                    val translateddata=it.toString()
                    display.text=translateddata
                }.addOnFailureListener {
                    Toast.makeText(this,"Sorry translation error occured",Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}