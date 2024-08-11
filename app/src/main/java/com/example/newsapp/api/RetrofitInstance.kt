package com.example.newsapp.api

import com.example.newsapp.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        private val RETROFIT by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            RETROFIT.create(NewsApi::class.java)
        }

    }
}

/*This code defines a RetrofitInstance class with a companion object that provides a convenient way to access a Retrofit instance and its corresponding API service. Let's break down the implementation:
Companion object: In Kotlin, a companion object is similar to a static block in Java. It allows you to define properties and functions that belong to the class itself, rather than instances of the class.
RETROFIT by lazy { ... }: This defines a property named RETROFIT that holds the Retrofit instance. The lazy keyword ensures that the Retrofit instance is created only when it's first accessed and then cached for subsequent use. This is an efficient way to initialize objects that might not be needed immediately.
HttpLoggingInterceptor: This interceptor is used to log network requests and responses. It's helpful for debugging and understanding the communication between your app and the API.
OkHttpClient.Builder: This is used to configure the OkHttpClient that Retrofit will use for making network requests. In this case, the HttpLoggingInterceptor is added to the client.
Retrofit.Builder: This is used to configure the Retrofit instance. The base URL for the API is set, and a GsonConverterFactory is added to handle JSON serialization and deserialization.
api by lazy { ... }: This defines another lazy property named api that holds the API service interface. The RETROFIT.create() method is used to generate an implementation of the NewsApi interface, which can then be used to make API calls. In summary, this code provides a singleton Retrofit instance with a logging interceptor and a Gson converter. The api property provides easy access to the API service, allowing you to make network requests with Retrofit. This pattern is commonly used in Android development to encapsulate the Retrofit setup and provide a clean way to access the API service throughout the application.*/