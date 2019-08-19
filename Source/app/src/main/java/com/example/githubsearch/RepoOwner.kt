package com.example.githubsearch

import com.google.gson.annotations.SerializedName

data class Owner (
    @SerializedName("login") val login : String,
    @SerializedName("avatar_url") val avatar_url : String

    /**
     *
     *
    @SerializedName("url") val url : String,
    @SerializedName("html_url") val html_url : String,
    @SerializedName("followers_url") val followers_url : String,
    @SerializedName("following_url") val following_url : String,
    @SerializedName("repos_url") val repos_url : String,

     */
    )