package com.example.movieappcompose

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") searchTerm: String,
        @Query("apiKey") apikey: String
    ): Response<MovieSearchResponse>
}

data class MovieSearchResponse(
    val search: List<Movie>,
    val rotalResult:Int,
    val response: String
)