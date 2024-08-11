package com.example.newsapp.repository

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.database.ArticleDatabase
import com.example.newsapp.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {

    //Http Requests
    suspend fun getHeadlines(countryCode: String, pageNumber: Int) = RetrofitInstance.api.getTopHeadlines(countryCode = countryCode, pageNumber = pageNumber)


    suspend fun searchNews(pageNumber: Int, searchQuery: String) = RetrofitInstance.api.searchForNews(searchQuery = searchQuery, pageNumber = pageNumber)


    //Database Methods
    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getFavoriteNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}