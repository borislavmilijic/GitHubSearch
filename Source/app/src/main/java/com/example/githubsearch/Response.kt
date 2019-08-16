package com.example.githubsearch

import android.content.ClipData
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("total_count") val total_count : Int,
    @SerializedName("incomplete_results") val incomplete_results : Boolean,
    @SerializedName("items") val items : List<RepoItems>
)

