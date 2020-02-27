package com.example.colorpicker3_2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ColorViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.color_item, parent, false)) {

    private var cNameView: TextView? = null
    private var cValView: TextView? = null
    private var cBoxView: SurfaceView? = null


    init {
        cNameView = itemView.findViewById(R.id.color_name)
        cValView = itemView.findViewById(R.id.color_val)
        cBoxView = itemView.findViewById(R.id.square_color)
    }

    fun bind(color: SavedColor){
        cNameView?.text = color.color_name
        val temp = color.red_value.toString() + "  " + color.green_value.toString() + " " + color.blue_value.toString()
        cValView?.text = temp
        cBoxView?.setBackgroundColor(Color.rgb(color.red_value, color.green_value, color.blue_value))

    }
}