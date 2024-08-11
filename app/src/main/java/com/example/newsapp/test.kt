package com.example.newsapp

data class test(
    val articles: List<Article> = listOf(),
    val status: String = "",
    val totalResults: Int = 0
) {
    data class Article(
        val author: String = "",
        val content: Any? = null,
        val description: Any? = null,
        val publishedAt: String = "",
        val source: Source = Source(),
        val title: String = "",
        val url: String = "",
        val urlToImage: Any? = null
    ) {
        data class Source(
            val id: String = "",
            val name: String = ""
        )
    }
}