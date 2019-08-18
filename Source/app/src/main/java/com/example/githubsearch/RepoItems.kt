package com.example.githubsearch

import com.google.gson.annotations.SerializedName

data class RepoItems (
        //Data to Fetch from API Items
        @SerializedName("name") val name : String,
        @SerializedName("full_name") val full_name: String,
        @SerializedName("owner") val owner : Owner,
        @SerializedName("description") val description : String,
        @SerializedName("forks") val forks : Int,
        @SerializedName("stargazers_count") val stargazers_count : Int,
        @SerializedName("language") val language : String,
        @SerializedName("html_url") val html_url : String
    )
