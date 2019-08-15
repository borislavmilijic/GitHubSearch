package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    var search_field: SearchView? = null
    var test1_text: TextView? = null
    var test2_text: TextView? = null
    private val fetch_api by lazy { API.create() }
    private var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_field?.setOnClickListener(View.OnClickListener {
            //TODO
        })

        search_field = findViewById(R.id.search_bar_user)
        test1_text = findViewById(R.id.test1)
        test2_text = findViewById(R.id.test2)

        getRepo()
    }

    fun showResult(result:String) {
        test1_text?.setText(result.get(2).toString())
        test2_text?.setText(result.get(1).toString())
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




