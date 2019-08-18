package com.example.githubsearch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import androidx.recyclerview.widget.DividerItemDecoration



class MainActivity : AppCompatActivity(), DataAdapter.OnRepoListener {

    var query: String = ""
    var response: MutableList<RepoItems> = mutableListOf()
    var visibleItemCount: Int = 0
    var lastVisibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var loading: Boolean = false
    var per_page: Int = 30
    var page_num: Int = 1

    lateinit var repoAdapter: DataAdapter
    lateinit var repoRecycle: RecyclerView
    lateinit var search: SearchView
    lateinit var layoutManager: LinearLayoutManager

  //  private var

    //TODO --> Clickable Items
    //TODO --> Type-ahead Search
    //TODO --> Pagination

   // @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        search = findViewById(R.id.search)
        search.isIconfiedByDefault
        search.isSubmitButtonEnabled
        repoRecycle = findViewById(R.id.repo_list)
        repoAdapter = DataAdapter(response, this)
        repoRecycle.layoutManager = LinearLayoutManager(this)
        repoRecycle.adapter = repoAdapter
        layoutManager = LinearLayoutManager(this)

        val mDividerItemDecoration = DividerItemDecoration(
            repoRecycle.getContext(),
            layoutManager.getOrientation()
        )
        repoRecycle.addItemDecoration(mDividerItemDecoration)

        repoRecycle.setHasFixedSize(true)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    query = p0.toLowerCase()
                    getData(query)
                } else
                    Toast.makeText(applicationContext, "Your search query is empty! Try again.", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        repoRecycle.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                lastVisibleItemCount = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemCount >= totalItemCount) {
                    if (!loading && dy>0) {
                        loading = false
                        page_num++
                        getData(query)
                    }
                }
            }
        })
    }

    @SuppressLint("CheckResult")
    private fun getData(user_query: String) {
        val consumeAPI = API.create().getRepos(user_query, page_num, per_page)

        consumeAPI
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
            result-> repoAdapter = DataAdapter(result.items, this)
                repoRecycle.adapter = repoAdapter }, {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
            })
    }

    override fun onRepoClick(position: Int) {
        val intent = Intent(this, RepoDetailView::class.java)
        startActivity(intent)
    }
}




