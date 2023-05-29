package com.example.movieappcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.entity.FavMovie
import com.example.movieappcompose.repo.FavRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel (private val favRepo: FavRepo) : ViewModel(){

    private val favBtnStateFlow = MutableStateFlow<ImageVector>(Icons.Filled.FavoriteBorder)
    private val listFavMovie = MutableLiveData<List<FavMovie>>()
//    snackbarHostState = remember {SnackbarHostState()}
    val favBtn: MutableStateFlow<ImageVector> get() = favBtnStateFlow



    fun addtoFavMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            val favMovie = FavMovie(
                movie.id,
                movie.title,
                movie.poster,
                movie.year,
            )
            favRepo.addFavMovie(favMovie)

        }
    }

    fun deleteFavMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            val favMovie = FavMovie(
                movie.id,
                movie.title,
                movie.poster,
                movie.year
            )
            favRepo.deleteFavMovie(favMovie)

        }
    }
    fun getFavMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            val favcharacters = favRepo.getFavMovie()
            listFavMovie.postValue(favcharacters)
//

        }
    }
}