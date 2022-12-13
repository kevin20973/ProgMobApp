package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//variavel constante para o URL
const val BASE_URL ="https://fa2097324277.azurewebsites.net/"


class MainActivity : AppCompatActivity() {

    lateinit var Adapter: Adapter
    lateinit var  linearLayoutManager: LinearLayoutManager
    var searchView:androidx.appcompat.widget.SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById<SearchView>(R.id.search_view)


        recycler_main.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recycler_main.layoutManager = linearLayoutManager

        getMyData()
    }

    private fun getMyData(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(API::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<DataItem>?> {
            override fun onResponse(
                call: Call<List<DataItem>?>,
                response: Response<List<DataItem>?>
            ) {
                val responseBody = response.body()!!

                    Adapter = Adapter(baseContext, responseBody)
                    Adapter.notifyDataSetChanged()
                    recycler_main.adapter = Adapter

            }

            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {
                d("MainActivity", "onFailure" + t.message)
            }


        })

    }

    }







    
