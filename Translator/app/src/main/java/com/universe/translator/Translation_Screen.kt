package com.universe.translator

import android.app.Dialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.ktx.Firebase
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModel
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.common.model.RemoteModelSource
import com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager
import com.google.mlkit.common.sdkinternal.model.RemoteModelLoader
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import java.util.Collections.list

class Translation_Screen : AppCompatActivity() {
    lateinit var spinner1:Spinner
    lateinit var spinner2:Spinner
    var index1:Int=0
    var index2:Int=0
    lateinit var view:TextView
    lateinit var animedailog:Dialog
    lateinit var germandialog:Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation_screen)
        supportActionBar?.show()
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.themecolor)))
        spinner1=findViewById(R.id.spinner1)
        spinner2=findViewById(R.id.spinner2)
        view=findViewById(R.id.textView6)
        animedailog= Dialog(this)
        val textdata:EditText=findViewById(R.id.et2)
        val tranlate:ImageButton=findViewById(R.id.imageButton2)
        inflateview()
        getsourceandtargetlanguage()
        tranlate.setOnClickListener {
            val texttotranslate=textdata.text.toString()
            TranslateText(texttotranslate)
        }


    }




    fun inflateview(){
        val spinneritems= listOf(
            "English" to R.drawable.eng,
            "Spanish" to R.drawable.spanish,
            "German" to R.drawable.german,
            "French" to R.drawable.french,
            "Chinese" to R.drawable.chinese
        )
        spinner1.adapter=custom_spinner(this,spinneritems)

        val spinner2items=listOf(
            "Spanish" to R.drawable.spanish,
            "German" to R.drawable.german,
            "English" to R.drawable.eng,
            "Chinese" to R.drawable.chinese,
            "French" to R.drawable.french
        )
        spinner2.adapter=custom_spinner(this,spinner2items)

    }

    fun getsourceandtargetlanguage(){
        spinner1.onItemSelectedListener=object: AdapterView.OnItemSelectedListener {
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


    fun TranslateText(data:String){
       val options=TranslatorOptions.Builder()
        when(index1){
            0 -> options.setSourceLanguage(TranslateLanguage.ENGLISH)
            1 -> options.setSourceLanguage(TranslateLanguage.SPANISH)
            2 -> options.setSourceLanguage(TranslateLanguage.GERMAN)
            3 -> options.setSourceLanguage(TranslateLanguage.FRENCH)
            4 -> options.setSourceLanguage(TranslateLanguage.CHINESE)
        }
        when(index2){
            0 -> options.setTargetLanguage(TranslateLanguage.SPANISH)
            1 -> options.setTargetLanguage(TranslateLanguage.GERMAN)
            2 -> options.setTargetLanguage(TranslateLanguage.ENGLISH)
            3 -> options.setTargetLanguage(TranslateLanguage.CHINESE)
            4 -> options.setTargetLanguage(TranslateLanguage.FRENCH)
        }


        val languagetranslator=Translation.getClient(options.build())


        Handler().postDelayed({
            checkdownload()
        },1500)
        languagetranslator.downloadModelIfNeeded().addOnCompleteListener {
            if(it.isComplete){
                animedailog.dismiss()
                languagetranslator.translate(data).addOnSuccessListener {
                    val translatedtext=it.toString()
                    view.text=translatedtext
                }
            }
            else{
            Toast.makeText(this,"Sorry some error ocurred",Toast.LENGTH_LONG).show()
            }
        }

    }

    fun showanimedialog(){
        animedailog.setContentView(R.layout.animation)
        animedailog.setCancelable(false)
        animedailog.show()
    }


    fun checkdownload(){
        val downloadmanager=getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val query=DownloadManager.Query()
        query.setFilterByStatus(DownloadManager.STATUS_RUNNING)
        val cursor=downloadmanager.query(query)
        if(cursor.moveToFirst()){
            showanimedialog()
        }
        cursor.close()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.gallery){
            val intent=Intent(this@Translation_Screen,image_translation::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }




    }


