package com.example.fedorinchik_hw7

import com.example.fedorinchik_hw7.GiphConstants.Companion.MY_KEY
import retrofit2.Response

class Repository(private val api: GiphyApi) {

    suspend fun provideGiphy(query: String): Response<GiphyData> {
        return api.getGiphy(MY_KEY, 25, query)
    }

    suspend fun startGiphy(): Response<GiphyData> {
        return api.getGiphyTrend(MY_KEY, 25)
    }
}