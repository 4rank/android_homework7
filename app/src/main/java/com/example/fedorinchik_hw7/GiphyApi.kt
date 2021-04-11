package com.example.fedorinchik_hw7

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("trending")
    suspend fun getGiphyTrend(
        @Query("api_key") key: String,
        @Query("limit") giphLimit: Int
    ): Response<GiphyData>

    @GET("search")
    suspend fun getGiphy(
        @Query("api_key") key: String,
        @Query("limit") giphLimit: Int,
        @Query("q") category: String
    ): Response<GiphyData>
}