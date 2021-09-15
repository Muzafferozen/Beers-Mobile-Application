package com.muzafferozen.beers.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muzafferozen.beers.R
import com.muzafferozen.beers.adapter.RecyclerViewAdapter
import com.muzafferozen.beers.databinding.ActivityMainBinding
import com.muzafferozen.beers.model.BeersModel
import com.muzafferozen.beers.service.BeersAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://api.punkapi.com/"
    private var beersModels: ArrayList<BeersModel>? = null
    private var recyclerVİewAdapter: RecyclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadData()
    }
    private fun loadData() {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(BeersAPI::class.java)
        val call = service.getData()
        call.enqueue(object : Callback<List<BeersModel>> {
            override fun onResponse(
                call: Call<List<BeersModel>>,
                response: Response<List<BeersModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        beersModels = ArrayList(it)
                        beersModels?.let {
                            recyclerVİewAdapter = RecyclerViewAdapter(it, this@MainActivity)
                            recyclerView.adapter = recyclerVİewAdapter
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<BeersModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
    override fun onItemClick(beersModel: BeersModel) {
        val intent = Intent(this, AnotherActivity::class.java)
        // start your next activity
        intent.putExtra("description",beersModel.description)
        intent.putExtra("brewers_tips", beersModel.brewers_tips)
        intent.putExtra("image_url",beersModel.image_url)
        startActivity(intent)
        finish()
        //Toast.makeText(this, "Clicked : ${beersModel.name} ", Toast.LENGTH_LONG).show()
    }

}