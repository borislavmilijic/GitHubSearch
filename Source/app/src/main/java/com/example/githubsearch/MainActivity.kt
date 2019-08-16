package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    var search_field: SearchView? = null
    var repo_list: RecyclerView? = null

    private lateinit var repoAdapter: RepoAdapter

 //   private val fetch_api by lazy { API.create() }
 //   private var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_field?.setOnClickListener(View.OnClickListener {
            //TODO
        })

        search_field = findViewById(R.id.search_bar_user)
        repo_list = findViewById(R.id.repo_list)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val consumeAPI = retrofit.create(API::class.java)
        consumeAPI.getReposObject()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                var jsonDataModel : JsonDataModel = it
                repoAdapter.setRepos(it.data)
            }, {
            })




       // getRepo()


    }

    inner class RepoAdapter: RecyclerView.Adapter<>() {

        private val repos: List<RepoItems> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
            return RepoViewHolder(layoutInflater.inflate(R.layout.repo_row_layout, parent, false))
        }


        override fun getItemCount(): Int {
            return repos.size
        }

        override fun onBindViewHolder(holder: RepoViewHolder, position: Int {
            holder.bindModel(repos[position])
        }

        fun setRepos(data: List<RepoItems>) {
            repos.addAll(data)

        }

        inner class RepoViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {

            val repo_name: TextView = findViewById(R.id.repo_name)
            val repo_owner: TextView = findViewById(R.id.repo_owner)
            val repo_description: TextView = findViewById(R.id.repo_description)

            fun bindModel(Repo: RepoItems) {
                repo_name.text = repos.

            }
        }
    }

    /**

    private fun getRepo() {
        disposable =
            fetch_api.getReposObject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> Toast.makeText(this, result[1].toString(), Toast.LENGTH_LONG).show() },
                    { error -> Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()}
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    */

}




