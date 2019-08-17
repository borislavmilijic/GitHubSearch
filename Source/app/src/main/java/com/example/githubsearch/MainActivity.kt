package com.example.githubsearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var query: String = ""
    var response: MutableList<RepoItems> = mutableListOf()

    //lateinit var progressBar: ProgressBar

    var visibleItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var loading: Boolean = false
    var page_id: Int = 1


    lateinit var repoAdapter: DataAdapter
    lateinit var repoRecycle: RecyclerView
    lateinit var search: SearchView
    lateinit var layoutManager: LinearLayoutManager

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        search = findViewById(R.id.search)
        search.isIconfiedByDefault
        search.isSubmitButtonEnabled
        repoRecycle = findViewById(R.id.repo_list)
        repoAdapter = DataAdapter(response)
        repoRecycle.layoutManager = LinearLayoutManager(this)
        repoRecycle.adapter = repoAdapter
        layoutManager = LinearLayoutManager(this)



        repoRecycle.setHasFixedSize(true)
        getData(page_id.toString())


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


        /**

        val consumeAPI = API.create(query).getRepos()

        consumeAPI
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
               // result -> resultList = result.items
               result -> repoAdapter.setData(result.items)
            }, {Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
            })

        */


        println()

    }

    private fun getData(page_id: String) {
       // progressBar.visibility = View.VISIBLE
        var apiService: ApiService = RetrofitClient.getClient()!!.create(ApiService::class.java)
        var call: Call<List<Response>> = apiService.getRepos(page_id)
        call.enqueue(object: Callback<List<Response>>{
            override fun onFailure(call: Call<List<Response>>, t: Throwable) {
                try {
                    Log.d("tag",t!!.message)
                } catch(e: Exception) {

                }
            }
            override fun onResponse(call: Call<List<Response>>, response: retrofit2.Response<List<Response>>) {
                if(response!!.code() == 200) {
                    loading = true
                    setUpAdapter(response.body())
                } else {

                }
            }

        })
    }

    private fun setUpAdapter(body: List<Response>?) {

        var items_list: List<RepoItems>? = body?.get(1)?.items

        if(response.size==0) {
            response = items_list as MutableList<RepoItems>
            repoAdapter = DataAdapter(response)
            repoRecycle.adapter = repoAdapter
        } else {
            var currentPosition = (repoRecycle.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            response.addAll(body!![1].items)
            repoAdapter.notifyDataSetChanged()
        }
        repoRecycle.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItemCount = (repoRecycle.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (loading) {
                        if((visibleItemCount + pastVisibleItemCount)>= totalItemCount) {
                            loading = false
                            page_id++
                            getData(page_id.toString())
                        }
                    }
                }
                        }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
}




