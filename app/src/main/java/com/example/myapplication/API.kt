package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    //gets all news
    @GET(value = "api/GetNews")
    fun getData(): Call<List<DataItem>>

    //gets news by category
    @GET("api/getByCategory")
    fun getDataByCategory(
        @Query("categories") category:String
    ): Call<List<DataItem>>

    //gets news through the search bar
    @GET("api/getNewsBySearch")
    fun getDataBySearch(
        @Query("title") title:String
    ): Call<List<DataItem>>
}