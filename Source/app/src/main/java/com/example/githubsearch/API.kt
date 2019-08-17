package com.example.githubsearch

import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

object Constants {
    const val BASE_URL = "https://api.github.com/search/"
    const val REPO_URL = "repositories?q=tetris"

}

interface API {

    @GET(Constants.REPO_URL)

    fun getRepos(): Observable<Response>

    companion object {
        fun create(userQuery: String): API {

            val BASE_URL = "https://api.github.com/search/"
            val REPO_URL = "repositories?q="
            val finalURL: String = BASE_URL+REPO_URL+userQuery

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(API::class.java)
        }
    }
}



