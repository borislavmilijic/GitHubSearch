/**
 * @author: Borislav Milijic
 */

package com.example.githubsearch

import com.google.gson.annotations.SerializedName

data class Response(
    //Data to Fetch from API
    @SerializedName("total_count") val total_count : Int,
    @SerializedName("items") val items : MutableList<RepoItems>
)

