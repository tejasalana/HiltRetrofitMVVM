package com.example.hiltretrofitmvvm.api

import com.example.hiltretrofitmvvm.data.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

}