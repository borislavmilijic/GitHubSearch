package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    var search_field: SearchView? = null
    var tv_user: TextView? = null
    val fetch_api by lazy { API.create() }
    var disposable: Disposable? = null

    val random_req = "https://api.github.com/search/repositories?q=tetris+in:readme,description,name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_user = findViewById(R.id.tv_users)
        search_field = findViewById(R.id.search_bar_user)

        getRepo()
    }

    fun showResult(result:String) {
        tv_user?.setText(result)
    }

    fun showError(e: String?) {
        println(e)
    }

    private fun getRepo() {
        disposable =
            fetch_api.getReposObject("borislav")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> showResult(result.items.name.repo_name) },
                    { error -> showError(error.message) }
                )
    }

}




