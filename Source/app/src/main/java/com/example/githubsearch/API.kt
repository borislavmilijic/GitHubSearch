package com.example.githubsearch

import retrofit2.http.GET
import io.reactivex.Observable

interface API {

    @GET("repositories?q=kurac+in:description")
    fun getRepos(): Observable<Response>

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



