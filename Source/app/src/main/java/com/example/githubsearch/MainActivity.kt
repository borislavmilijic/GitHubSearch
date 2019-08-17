package com.example.githubsearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.AdapterView
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
    var query: String = ""

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    lateinit var resultList: List<RepoItems>

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

        var myLayoutManager = LinearLayoutManager(this)

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
                if (p0 != null && p0.isNotEmpty()) {
                    query = p0.toLowerCase()
                    Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()

                } else
                    Toast.makeText(applicationContext, "Your search query is empty! Try again.", Toast.LENGTH_SHORT).show()

                return false
            }
        })

        val consumeAPI = API.create(query).getRepos()

        consumeAPI
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                result -> resultList = result.items
              //  result -> repoAdapter.setData(result.items)
            }, {Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
            })



        repoRecycle.addOnScrollListener(object : PaginationScrollListener(myLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getMoreItems()
            }

        })
    }

    fun getMoreItems() {
        isLoading = false
        repoAdapter.setData(resultList)

    }
}




