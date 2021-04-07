package com.example.fedorinchik_hw7

import com.example.fedorinchik_hw7.GiphConstants.Companion.MY_KEY
import retrofit2.Response

class GiphyRepository {

    suspend fun takeGiphy(request: String): Response<GifData> {
        return Retrofit.api.getGifs(MY_KEY, 30, request)
    }

    suspend fun startTrend(): Response<GifData> {
        return Retrofit.api.getTrendGifs(MY_KEY, 30)
    }
}