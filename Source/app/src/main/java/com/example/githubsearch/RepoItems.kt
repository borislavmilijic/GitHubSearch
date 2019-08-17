package com.example.githubsearch

import com.google.gson.annotations.SerializedName

data class RepoItems (
        @SerializedName("id") val id : Int,
        @SerializedName("name") val name : String,
        @SerializedName("owner") val owner : Owner,
        @SerializedName("description") val description : String,
        @SerializedName("url") val url : String

  /**
   *
        @SerializedName("forks_url") val forks_url : String,
        @SerializedName("html_url") val html_url : String,
        @SerializedName("created_at") val created_at : String,
        @SerializedName("updated_at") val updated_at : String,
        @SerializedName("git_url") val git_url : String,
        @SerializedName("stargazers_count") val stargazers_count : Int,
        @SerializedName("watchers_count") val watchers_count : Int,
        @SerializedName("language") val language : String,
        @SerializedName("forks") val forks : Int,

   */
    )
