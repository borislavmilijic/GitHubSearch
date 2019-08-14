package com.example.githubsearch

class Data internal constructor(
    var repo_name: String,
    var repo_owner: String,
    var repo_description: String,
    var repo_id: Int,
    var repo_stars: Int,
    var repo_forks: Int
)