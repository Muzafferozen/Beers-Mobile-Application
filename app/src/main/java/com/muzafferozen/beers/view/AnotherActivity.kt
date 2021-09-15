package com.muzafferozen.beers.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.muzafferozen.beers.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_another.*
import kotlinx.android.synthetic.main.activity_another.view.*

class AnotherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
        //intent.putExtra("img_url",beersModel.image_url)
        //intent.putExtra("Brewers_tips", beersModel.brewers_tips)
       val des = intent.getStringExtra("description");
       val tips = intent.getStringExtra("brewers_tips");
        val img = intent.getStringExtra("image_url");
        val textView : TextView = findViewById(R.id.brewersTips)
        textView.text = tips;
        val textView2 : TextView = findViewById(R.id.beerDescription)
        textView2.text =  des;
        val imageView3 : ImageView = findViewById(R.id.imageView)
        Picasso.get()
            .load(img)
            .into(imageView3)
    }
}