package com.example.newsapp.models

data class NewsResponse(
    val articles: MutableList<Article>? = emptyList<Article>().toMutableList(),
    val status: String? = "",
    val totalResults: Int? = 0
)