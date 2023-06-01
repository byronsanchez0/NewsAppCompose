package com.example.movieappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappcompose.FavoritesScreen
import com.example.movieappcompose.MovieSearchScreen
import com.example.movieappcompose.MoviesViewModel

@Composable
fun BottomNavGraph(navHostController: NavHostController, moviesViewModel: MoviesViewModel) {

    NavHost(
        navController = navHostController,
        startDestination = "moviesearchscreen"
    ) {
        composable("moviesearchscreen") {
            MovieSearchScreen(moviesViewModel)
        }
        composable(route = "favorites") {
            FavoritesScreen(moviesViewModel)
        }

    }
}
