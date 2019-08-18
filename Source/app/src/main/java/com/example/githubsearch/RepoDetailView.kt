package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RepoDetailView : AppCompatActivity() {
    lateinit var repo_full_name_view: TextView
    lateinit var repo_owner_name_view: TextView
    lateinit var repo_description_view: TextView
    lateinit var repo_language_view: TextView
    lateinit var repo_stars_count_view: TextView
    lateinit var repo_forks_count_view: TextView
    lateinit var repo_avatar_owner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail_view)

        repo_full_name_view = findViewById(R.id.repo_repo_name)
        repo_description_view = findViewById(R.id.repo_repo_description)
        repo_language_view = findViewById(R.id.repo_language)
        repo_owner_name_view = findViewById(R.id.repo_user_name)
        repo_stars_count_view = findViewById(R.id.repo_stars_count)
        repo_forks_count_view = findViewById(R.id.repo_forks_count)
        repo_avatar_owner = findViewById(R.id.repo_avatar)

        repo_description_view.text = intent.getStringExtra("repo_description")
        repo_language_view.text = intent.getStringExtra("repo_language")
        repo_owner_name_view.text = intent.getStringExtra("repo_owner_name")
        repo_full_name_view.text = intent.getStringExtra("repo_full_name")
        repo_stars_count_view.text = intent.getIntExtra("stars_count", 0).toString()
        repo_forks_count_view.text = intent.getIntExtra("forks_count", 0).toString()
        Picasso
            .get()
            .load(intent.getStringExtra("repo_avatar_url"))
            .into(repo_avatar_owner)

    }
}
