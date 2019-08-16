package com.example.githubsearch

import android.content.ClipData
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

interface API {

    @GET("repositories?q=tetris+in:readme")
    fun getReposObject(): Observable<JsonDataModel>

    /**

    companion object {
        fun create(): API {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/search/")
                .build()

            return retrofit.create(API::class.java)
        }
    }

    */
}



