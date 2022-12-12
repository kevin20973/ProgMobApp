package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//variavel constante para o URL
const val BASE_URL ="https://fa2097324277.azurewebsites.net/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

                val myStringBuilder = StringBuilder()
                for (myData in responseBody){
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }

            }

            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {

            }
        })
    }
}