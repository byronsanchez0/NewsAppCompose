package com.example.movieappcompose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MovieList (movies : List<Movie>){
    LazyColumn {
        items(movies.size) { movie ->
            Text(text = "a")
        }
    }
}