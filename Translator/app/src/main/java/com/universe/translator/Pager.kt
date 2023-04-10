package com.universe.translator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager


class Pager(var imglist:List<Int>,val context: Context): RecyclerView.Adapter<Pager.Pager2View>() {

    inner class Pager2View(itemview: View):RecyclerView.ViewHolder(itemview){
        val imageholder:ImageView=itemview.findViewById(R.id.imageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2View {
        val view=LayoutInflater.from(context).inflate(R.layout.pageritem,parent,false)
        return Pager2View(view)
    }

    override fun getItemCount(): Int {
        return imglist.size
    }

    override fun onBindViewHolder(holder: Pager2View, position: Int) {
        holder.imageholder.setImageResource(imglist[position])
    }
}