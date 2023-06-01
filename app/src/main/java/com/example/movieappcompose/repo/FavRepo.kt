package com.example.movieappcompose.repo

import com.example.movieappcompose.dao.FavMovieDao
import com.example.movieappcompose.entity.FavMovie

class FavRepo (private val favMovieDao: FavMovieDao) {
    fun addFavMovie(favMovie: FavMovie) {
        favMovieDao.insertAll(favMovie)
    }
    fun getFavMovie(userId: Long): List<FavMovie> {
        return favMovieDao.getById(userId)
    }
    fun deleteFavMovie(favMovie: FavMovie) {
        favMovieDao.delete(favMovie)
    }
}
