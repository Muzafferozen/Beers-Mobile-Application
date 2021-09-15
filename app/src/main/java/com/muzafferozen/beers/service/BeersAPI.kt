package com.muzafferozen.beers.service

import com.muzafferozen.beers.model.BeersModel
import retrofit2.Call
import retrofit2.http.GET

interface BeersAPI {

    @GET("/v2/beers/")
    fun getData (): Call<List<BeersModel>>
}