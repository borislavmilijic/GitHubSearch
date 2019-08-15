package com.example.githubsearch

object DataModel {
    data class Result(val items: Items)
    data class Items(val name: Name
                   //  val description: Description,
                   //  val owner: Owner
                    )
    data class Name(val repo_name: String)
//    data class Description(val repo_description: String)
//    data class Owner(val login: Login)
//    data class Login(val repo_owner: String)
}