package com.gifs.app.user.repo

import com.gifs.app.user.api.ApiService
import com.gifs.app.user.model.GifBaseModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped

class TestRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun getGifs(apiKey: String, query: String): GifBaseModel {
        return apiService.getGifs(apiKey, query)
    }
}