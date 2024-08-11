package com.example.newsapp.api

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.utils.Constants.Companion.API_Key
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey")
        apiKey: String = API_Key,
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = API_Key,
        @Query("page")
        pageNumber: Int = 1,
    ): Response<NewsResponse>

}