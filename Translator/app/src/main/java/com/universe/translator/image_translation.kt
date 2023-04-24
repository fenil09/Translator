package com.universe.translator

import android.app.Dialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.LruCache
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.mlkit.common.model.RemoteModel
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class image_translation : AppCompatActivity() {
    lateinit var imgview: ImageView
    lateinit var spinner1: Spinner
    lateinit var spinner2: Spinner
    var index1 = 0
    var index2 = 0
    lateinit var dialog: Dialog
    lateinit var resultview: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_translation)
        imgview = findViewById(R.id.imageView4)
        spinner1 = findViewById(R.id.spinner3)
        spinner2 = findViewById(R.id.spinner4)
        resultview = findViewById(R.id.textView8)
        val translate: ImageButton = findViewById(R.id.imageButton3)
        getitemfromspinner()
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.themecolor)))
        inflateview()
        translate.setOnClickListener {
            recognizetext()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items_2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.gallery2) {
            selectimagefromgallery()
        }
        else{
            if(item.itemId==R.id.camera){
                captureimage()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun selectimagefromgallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                imgview.setImageURI(data.data)
            }
        }
        else{

            if(requestCode==2 && resultCode== RESULT_OK){
                val extras= data?.extras
                val imgbitmap=extras?.get("data") as Bitmap
                imgview.setImageBitmap(imgbitmap)

            }
        }
    }


    fun inflateview() {
        val spinneritems = listOf(
            "English" to R.drawable.eng,
            "Spanish" to R.drawable.spanish,
            "German" to R.drawable.german,
            "French" to R.drawable.french,
            "Chinese" to R.drawable.chinese
        )
        spinner1.adapter = custom_spinner(this, spinneritems)


        val spinner2items = listOf(
            "Spanish" to R.drawable.spanish,
            "German" to R.drawable.german,
            "English" to R.drawable.eng,
            "Chinese" to R.drawable.chinese,
            "French" to R.drawable.french
        )
        spinner2.adapter = custom_spinner(this, spinner2items)
    }

    fun recognizetext() {
        val bitmap = (imgview.drawable as BitmapDrawable).bitmap
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val recognize = FirebaseVision.getInstance().onDeviceTextRecognizer
        recognize.processImage(image).addOnSuccessListener {
            val result = it.text
            translateimagetext(result)
        }.addOnFailureListener {
            Toast.makeText(this, "Sorry could not recognize the text", Toast.LENGTH_LONG).show()
        }
    }

    fun translateimagetext(data: String) {

        val options = TranslatorOptions.Builder()
        when (index1) {
            0 -> options.setSourceLanguage(TranslateLanguage.ENGLISH)
            1 -> options.setSourceLanguage(TranslateLanguage.SPANISH)
            2 -> options.setSourceLanguage(TranslateLanguage.GERMAN)
            3 -> options.setSourceLanguage(TranslateLanguage.FRENCH)
            4 -> options.setSourceLanguage(TranslateLanguage.CHINESE)
        }

        when (index2) {
            0 -> options.setTargetLanguage(TranslateLanguage.SPANISH)
            1 -> options.setTargetLanguage(TranslateLanguage.GERMAN)
            2 -> options.setTargetLanguage(TranslateLanguage.ENGLISH)
            3 -> options.setTargetLanguage(TranslateLanguage.CHINESE)
            4 -> options.setTargetLanguage(TranslateLanguage.FRENCH)
        }
        val languagetranslator = Translation.getClient(options.build())

        Handler().postDelayed({
            checkdownload()
        }, 1500)

        languagetranslator.downloadModelIfNeeded().addOnCompleteListener {

            if (it.isComplete) {
                dialog.dismiss()
                languagetranslator.translate(data).addOnSuccessListener {
                    val translateddata = it.toString()
                    resultview.text = translateddata
                }.addOnFailureListener {
                    Toast.makeText(this, "Sorry some error occured", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    fun getitemfromspinner(){
        spinner1.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                index1=position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                index2=position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    fun showanimedialog(){
        dialog= Dialog(this)
        dialog.setContentView(R.layout.animation)
        dialog.setCancelable(false)
        dialog.show()
    }


    fun checkdownload(){
        val downloadmanager=getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val query= DownloadManager.Query()
        query.setFilterByStatus(DownloadManager.STATUS_RUNNING)
        val cursor=downloadmanager.query(query)
        if(cursor.moveToFirst()){
            showanimedialog()
        }
        cursor.close()
    }


    fun captureimage(){
        val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,2)
    }



}