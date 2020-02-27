package com.example.colorpicker3_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ListAdapter(private val list: ArrayList<SavedColor>) : RecyclerView.Adapter<ColorViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ColorViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color: SavedColor = list[position]
        holder.bind(color)


    }

    override fun getItemCount(): Int = list.size





}