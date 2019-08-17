package com.example.githubsearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var repoAdapter: DataAdapter
    lateinit var repoRecycle: RecyclerView
    lateinit var search: SearchView
    lateinit var query: String

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        search = findViewById(R.id.search)
        search.isIconfiedByDefault
        search.isSubmitButtonEnabled
        repoRecycle = findViewById(R.id.repo_list)
        repoAdapter = DataAdapter(this)
        repoRecycle.layoutManager = LinearLayoutManager(this)
        repoRecycle.adapter = repoAdapter

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    query = p0.toLowerCase()
                }

                //TODO
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                //TODO
                if (p0 != null) {
                    query = p0.toLowerCase()
                } else
                    Toast.makeText(applicationContext, "Your search query is empty! Try again.", Toast.LENGTH_SHORT).show()

                return false
            }
        })

        val consumeAPI = API.create().getRepos()

        consumeAPI
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                result -> repoAdapter.setData(result.items)
            }, {Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
            })
    }
}




