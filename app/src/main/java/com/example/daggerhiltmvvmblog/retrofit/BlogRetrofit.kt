package com.example.daggerhiltmvvmblog.retrofit

import com.example.daggerhiltmvvmblog.model.Blog
import retrofit2.http.GET

interface BlogRetrofit {
    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>
}