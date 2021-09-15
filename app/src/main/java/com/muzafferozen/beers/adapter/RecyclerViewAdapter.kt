package com.muzafferozen.beers.adapter

import android.content.Intent
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muzafferozen.beers.R
import com.muzafferozen.beers.model.BeersModel
import com.muzafferozen.beers.view.AnotherActivity
import com.muzafferozen.beers.view.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter (private val beersList : ArrayList<BeersModel>,private val listener: Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {

        fun onItemClick (beersModel: BeersModel) {
        }
    }
    private val colors : Array<String> = arrayOf("#3bfd00","#00ffd2","#0045ff","#a300ff","#ff00e7","#ff0000","#ff00db","#ffe700")
    class RowHolder( view : View) : RecyclerView.ViewHolder(view) {
        fun bind(beersModel: BeersModel,colors : Array<String>,position: Int, listener: Listener){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.setOnClickListener{
                listener.onItemClick(beersModel)
            }
            itemView.text_name.text = beersModel.name
            itemView.text_tagline.text = beersModel.tagline
            Picasso.get()
                .load(beersModel.image_url)
                .into(itemView.imageView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }
    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(beersList[position],colors,position,listener)
    }
    override fun getItemCount(): Int {
        return beersList.count()
    }
}