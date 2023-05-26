package com.example.movieappcompose

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("imdbID")
    val id: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Released")
    val released: String,
    @SerializedName("Response")
    val response: String
)
