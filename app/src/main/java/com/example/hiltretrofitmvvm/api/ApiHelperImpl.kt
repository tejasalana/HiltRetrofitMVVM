package com.example.hiltretrofitmvvm.api

import com.example.hiltretrofitmvvm.data.Post
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override suspend fun getPosts(): Response<List<Post>> = apiService.getPosts()

}