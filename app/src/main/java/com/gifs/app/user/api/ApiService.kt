package com.gifs.app.user.api

import com.gifs.app.user.model.GifBaseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("gifs/search")
    suspend fun getGifs(
        @Query("api_key") key: String,
        @Query("q") query: String,
    ): GifBaseModel
}