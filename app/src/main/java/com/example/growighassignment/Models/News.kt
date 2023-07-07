package com.example.growighassignment.Models

data class News(
    var articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)