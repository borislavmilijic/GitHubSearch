/**
 * @author: Borislav Milijic
 */

package com.example.githubsearch

import com.google.gson.annotations.SerializedName

data class Owner (
    //JSON sub-object
    @SerializedName("login") val login : String,
    @SerializedName("avatar_url") val avatar_url : String
)