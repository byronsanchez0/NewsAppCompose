package com.example.movieappcompose

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("/")
    suspend fun searchMovies(
        @Query("apiKey") apikey: String,
        @Query("search") searchTerm: String

    ): Response<GuardianResponse>
}