package com.example.githubsearch

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object Constants {
    const val BASE_URL = "https://api.github.com/search/"
}

interface API {

    @GET("repositories")

    fun getRepos(
        @Query("q") userQuery: String,
        @Query("page") page_num: Int,
        @Query("per_page") per_page: Int
    ): Observable<Response>

    companion object {
        fun create(): API {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(API::class.java)
        }
    }
}



