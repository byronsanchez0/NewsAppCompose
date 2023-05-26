package com.example.movieappcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel(){
//      suspend fun searchMovies(searchTerm: String): List<Movie> {
//        val service = RetrofitClient.movieService
//
//         val apiKey = "34a8034"
//         val response = service.searchMovies(apiKey, searchTerm)
//         if (response.isSuccessful){
//             val searchResponse = response.body()
//             return searchResponse?.search?: emptyList()
//         } else {
//             throw Exception("Failed to search movies: ${response.errorBody()}")
//         }




//        viewModelScope.launch {
//            val response = service.searchMovies(searchTerm, RetrofitClient.API_KEY)
//            if (response.isSuccessful) {
//                val movieSearchResponse = response.body()
//                movieSearchResponse?.search?.let {
//                    movies.addAll(it)
//                    movies.clear()
//
//                }
//            }
//            println(movies.size)
//        }
//    }
}