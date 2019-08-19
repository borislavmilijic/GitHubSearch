/**
 * @author: Borislav Milijic
 */

package com.example.githubsearch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhexaville.simplerecyclerview.SimpleRecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), DataAdapter.OnRepoListener {
    var query: String = ""
    var response: MutableList<RepoItems> = mutableListOf()
    var per_page: Int = 20
    var page_num: Int = 1
    var sort = "stars"
    var readme = "readme"
    var description = "description"
    var name = "name"

    lateinit var repoAdapter: DataAdapter
    lateinit var repoRecycle: SimpleRecyclerView
    lateinit var search: SearchView
    lateinit var layoutManager: LinearLayoutManager

    var result_list: ArrayList<RepoItems> = arrayListOf()

    //TODO --> Type-ahead Search

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
        repoRecycle.setLayoutManager(LinearLayoutManager(this))
        repoRecycle.setAdapter(repoAdapter)
        layoutManager = LinearLayoutManager(this)

       //divider for RecycleView results
        val mDividerItemDecoration = DividerItemDecoration(
            repoRecycle.getContext(),
            layoutManager.getOrientation()
        )
        repoRecycle.addItemDecoration(mDividerItemDecoration)

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

       //loads the new data when on the end of list in RV
       repoRecycle.setOnLoadMoreListener {
           while (page_num<=(result_list.size/per_page)) {
           page_num++
           getData(query)
           }
       }
    }

    @SuppressLint("CheckResult")
    private fun getData(user_query: String) {
        val consumeAPI = API.create().getRepos(user_query, sort, page_num, per_page)

        consumeAPI
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
            result-> repoAdapter = DataAdapter(result.items, this)
                repoRecycle.setAdapter(repoAdapter)
                result_list.addAll(result.items)
                repoRecycle.setDoneFetching()
                }, {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
            })
    }

    //on Recycler view item click -> new Activity: (somewhat) Detailed Repository View
    override fun onRepoClick(position: Int) {
        val intent = Intent(this, RepoDetailView::class.java)

        //puting all the data for the new activity in intent
        intent.putExtra("repo_full_name",result_list[position].full_name)
        intent.putExtra("repo_owner_name",result_list[position].owner.login)
        intent.putExtra("repo_avatar_url", result_list[position].owner.avatar_url)
        intent.putExtra("stars_count", result_list[position].stargazers_count)
        intent.putExtra("forks_count", result_list[position].forks)
        intent.putExtra("repo_description", result_list[position].description)
        intent.putExtra("repo_language", result_list[position].language)
        intent.putExtra("repo_url", result_list[position].html_url)

        startActivity(intent)
    }
}




