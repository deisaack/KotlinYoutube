 package com.addictaf.kotlinyoutube.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.addictaf.kotlinyoutube.R
import com.addictaf.kotlinyoutube.adapters.MainAdapter
import com.addictaf.kotlinyoutube.models.HomeFeed
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

 class MainActivity : AppCompatActivity() {
     companion object {
         val TAG = "MAIN_ACT"
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_main.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

     private fun fetchJson() {
         Log.d(TAG,"Attempting to fetch")
         val url = "https://api.letsbuildthatapp.com/youtube/home_feed";
         val request = Request.Builder().url(url).build()
         val client = OkHttpClient();
         client.newCall(request).enqueue(object: Callback {
             override fun onFailure(call: Call, e: IOException) {
                 Log.d(TAG,"Failed to make request")
             }

             override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                 Log.d(TAG, body)
                 val gson = GsonBuilder().create()
                 val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                 runOnUiThread {
                     recyclerview_main.adapter = MainAdapter(homeFeed)
                 }
             }
         })
         
     }
 }
