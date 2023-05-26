package com.example.movieappcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel(){
     fun searchMovies(searchTerm: String, movies: MutableList<Movie>) {
        val service = RetrofitClient.movieService

        viewModelScope.launch {
            val response = service.searchMovies(searchTerm, RetrofitClient.API_KEY)
            if (response.isSuccessful) {
                val movieSearchResponse = response.body()
                movieSearchResponse?.search?.let {
                    movies.clear()
                    movies.addAll(it)
                }
            }
            println(movies.size)
        }
    }
}