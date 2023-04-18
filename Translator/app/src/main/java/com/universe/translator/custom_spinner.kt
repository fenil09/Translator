package com.universe.translator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class custom_spinner(context:Context,val items:List<Pair<String,Int>>):ArrayAdapter<Pair<String,Int>>(context,R.layout.spinner_items,items){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view=LayoutInflater.from(context).inflate(R.layout.spinner_items,parent,false)
        val item=items[position]
        view.findViewById<ImageView>(R.id.imageView3).setImageResource(item.second)
        view.findViewById<TextView>(R.id.textView7).text=item.first
        return view

    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=LayoutInflater.from(context).inflate(R.layout.spinner_items,parent,false)
        val item=items[position]
        view.findViewById<ImageView>(R.id.imageView3).setImageResource(item.second)
        view.findViewById<TextView>(R.id.textView7).text=item.first
        return view
    }

}