package com.example.githubsearch

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var search_field: SearchView? = null
    lateinit var repo_list: RecyclerView

    private lateinit var repoAdapter: RepoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_field?.setOnClickListener(View.OnClickListener {
            //TODO
        })

        search_field = findViewById(R.id.search_bar_user)
        repo_list = findViewById(R.id.repo_list)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val consumeAPI = retrofit.create(API::class.java)

        consumeAPI.getRepos()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ repoAdapter.setRepos(it.items) },
                {Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
            })

    }


    inner class RepoAdapter: RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

        private val repos: MutableList<RepoItems> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
            return RepoViewHolder(layoutInflater.inflate(R.layout.repo_row_layout, parent, false))
        }


        override fun getItemCount(): Int {
            return repos.size
        }

        override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
            holder.bindModel(repos[position])
        }

        fun setRepos(data: List<RepoItems>) {
            repos.addAll(data)
            repoAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, repos.size.toString(), Toast.LENGTH_LONG).show()
        }

        inner class RepoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            val repo_name: TextView = findViewById(R.id.repo_name)
            val repo_owner: TextView = findViewById(R.id.repo_owner)
            val repo_description: TextView = findViewById(R.id.repo_description)

            fun bindModel(repo: RepoItems) {

                repo_name.text = repo.name
                repo_owner.text = repo.owner.login
                repo_description.text = repo.description

            }
        }
    }
}




