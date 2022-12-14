package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

        var b1 = findViewById<Button>(R.id.btn_1)
        b1.setOnClickListener() {
            getMyDataByCategory("Mundo")
        }
        var b2 = findViewById<Button>(R.id.btn_2)
        b2.setOnClickListener() {
            getMyDataByCategory("Ultimas")
        }
        var b3 = findViewById<Button>(R.id.btn_3)
        b3.setOnClickListener() {
            getMyDataByCategory("Desporto")
        }
        var b4 = findViewById<Button>(R.id.btn_4)
        b4.setOnClickListener() {
            getMyDataByCategory("Politica")
        }
        var b5 = findViewById<Button>(R.id.btn_5)
        b5.setOnClickListener() {
            getMyDataByCategory("Economia")

        }
        search_view.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search_view.clearFocus()
                getMyDataBySearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    private fun getMyData(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(API::class.java)
        //vai buscar a data ao DataItem através do builder do retrofit
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<DataItem>?> {
            //função onResponse e onFailure geradas automaticamente quando gerado o objeto
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

    private fun getMyDataByCategory(category: String){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(API::class.java)

        val retrofitData = retrofitBuilder.getDataByCategory(category)

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

    private fun getMyDataBySearch(query: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(API::class.java)

        val retrofitData = retrofitBuilder.getDataBySearch("%"+query+"%")

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

    fun OnNewsClicked(headlines: DataItem?) {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)

    }}







    
