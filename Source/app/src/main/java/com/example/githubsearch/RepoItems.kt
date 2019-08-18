package com.example.githubsearch

import com.google.gson.annotations.SerializedName

data class RepoItems (
        @SerializedName("name") val name : String,
        @SerializedName("full_name") val full_name: String,
        @SerializedName("owner") val owner : Owner,
        @SerializedName("description") val description : String,
        @SerializedName("forks") val forks : Int,
        @SerializedName("stargazers_count") val stargazers_count : Int,
        @SerializedName("language") val language : String,
        @SerializedName("html_url") val html_url : String

        /**
         *
        @SerializedName("forks_url") val forks_url : String,
        @SerializedName("created_at") val created_at : String,
        @SerializedName("updated_at") val updated_at : String,
        @SerializedName("git_url") val git_url : String,
        @SerializedName("watchers_count") val watchers_count : Int,
        @SerializedName("url") val url : String,
        @SerializedName("id") val id : Int,

         */
    )
