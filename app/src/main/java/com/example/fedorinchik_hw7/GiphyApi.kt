package com.example.fedorinchik_hw7

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("trending")
    suspend fun getTrendGifs(
        @Query("api_key") key: String,
        @Query("limit") giphLimit: Int
    ): Response<GifData>

    @GET("search")
    suspend fun getGifs(
        @Query("api_key") key: String,
        @Query("limit") giphLimit: Int,
        @Query("q") category: String
    ): Response<GifData>
}