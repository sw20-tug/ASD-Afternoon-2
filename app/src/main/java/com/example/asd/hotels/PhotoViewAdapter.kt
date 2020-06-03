package com.example.asd.hotels

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import kotlinx.android.synthetic.main.hotel_layout.view.*
import kotlinx.android.synthetic.main.photo_layout.view.*

class PhotoViewAdapter(private val images: List<Int>,
                       private val photoViewClicked: () -> Unit) :
    RecyclerView.Adapter<PhotoViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(
            R.layout.photo_layout,
            parent, false
        )
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val path = images[position]
        // display image
        holder.view.photoview_item.setImageDrawable(
            holder.view.context.getDrawable(path)
        )
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return images.size
    }

}