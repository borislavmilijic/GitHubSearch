package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import android.widget.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    private val search_field: SearchView? = null
    private val rec_view: RecyclerView? = null

    val random_req = "https://api.github.com/search/repositories?q=tetris+in:readme,description,name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    companion object {
        const val BASE_URL = "https://api.github.com/search/repositories?"
        const val filter1 = ""
        const val filter2 = ""
    }
}




