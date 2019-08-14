package com.example.githubsearch

import android.os.AsyncTask
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONException


object Parser {

    /*

    fun parseRepos (get_responce :String) :ArrayList<> {
        val parser = JsonParser()
        val result = null
        val array = parser.parse(get_responce).asJsonArray



    }
    */
}


class FetchData() : AsyncTask< Void,Void,Void > () {

    override fun doInBackground(vararg p0: Void?): Void {
        //TODO
    }

    var url: String? = ""

    override  fun onPreExecute() {
        super.onPreExecute()
        // ...
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        // ...
    }
}