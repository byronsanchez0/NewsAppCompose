package com.example.movieappcompose

import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.Converter.Factory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
//    private const val BASE_URL = "https://www.omdbapi.com/"
//    const val API_KEY = "34a8034"
//
//    private val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//    }
//
//
//    val movieService: MoviesService by lazy{
//        retrofit.create(MoviesService::class.java)
//    }
private const val BASE_URL = "https://www.omdbapi.com/"

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val omdbApiService: MoviesService by lazy {
        retrofit.create(MoviesService::class.java)
    }
//    java.lang.NullPointerException: Parameter specified as non-null is null: method androidx.compose.material3.TextKt.Text--4IGK_g, parameter text
    suspend fun searchMovies(query: String): List<Movie> {
        val apiKey = "34a8034"
        val response = omdbApiService.searchMovies(apiKey, query)
        if (response.isSuccessful){
            val searchResponse = response.body()
            return searchResponse?.search ?: emptyList()
        } else {
            throw Exception("Failed to search movies: ${response.errorBody()}")
        }
    }
}