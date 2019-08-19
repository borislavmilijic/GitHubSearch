package com.example.githubsearch

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("total_count") val total_count : Int,
    @SerializedName("items") val items : MutableList<RepoItems>
)

