package com.example.fedorinchik_hw7

import com.example.fedorinchik_hw7.GiphConstants.Companion.RESOURSE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    val api: GiphyApi by lazy {
        retrofit.create(GiphyApi::class.java)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RESOURSE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}