package com.example.githubsearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("repositories?q=tetrin+in:readme")

    fun getRepos(@Query("page") page: String) : Call<List<Response>>
}