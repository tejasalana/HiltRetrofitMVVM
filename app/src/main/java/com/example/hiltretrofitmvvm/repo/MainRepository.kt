package com.example.hiltretrofitmvvm.repo

import com.example.hiltretrofitmvvm.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
){

    suspend fun getPosts() = apiHelper.getPosts()

}