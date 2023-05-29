package com.example.movieappcompose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieappcompose.dao.FavMovieDao
import com.example.movieappcompose.entity.FavMovie

@Database(entities = [FavMovie::class], version = 1)
abstract class FavDataBase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "favdatabase"
    }
    abstract fun favMoviesDao(): FavMovieDao
}