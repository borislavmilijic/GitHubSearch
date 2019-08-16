package com.example.githubsearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var repoAdapter: DataAdapter
    lateinit var repoRecycle: RecyclerView

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repoRecycle = findViewById(R.id.repo_list)
        repoAdapter = DataAdapter(this)
        repoRecycle.layoutManager = LinearLayoutManager(this)
        repoRecycle.adapter = repoAdapter

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




