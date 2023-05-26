package com.example.movieappcompose

import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.Converter.Factory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_URL = "http://www.omdbapi.com/"
    const val API_KEY = "34a8034"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }


    val movieService: MoviesService by lazy{
        retrofit.create(MoviesService::class.java)
    }
}