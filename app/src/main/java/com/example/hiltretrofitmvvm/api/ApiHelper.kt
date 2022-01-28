package com.example.hiltretrofitmvvm.api

import com.example.hiltretrofitmvvm.data.Post
import retrofit2.Response

interface ApiHelper {

    suspend fun getPosts(): Response<List<Post>>
}