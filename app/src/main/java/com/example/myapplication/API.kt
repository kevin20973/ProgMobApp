package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET(value = "api/GetNews")
    fun getData(): Call<List<DataItem>>
}