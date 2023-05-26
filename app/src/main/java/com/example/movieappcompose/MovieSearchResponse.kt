package com.example.movieappcompose

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("Search")
    val search: List<Movie>,
    val totalResult:Int,
)
