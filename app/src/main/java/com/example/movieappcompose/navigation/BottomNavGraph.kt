package com.example.movieappcompose.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappcompose.FavoritesScreen
import com.example.movieappcompose.MovieSearchScreen
import com.example.movieappcompose.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavGraph(navHostController: NavHostController, moviesViewModel: MoviesViewModel) {

//    val cameraViewModelaViewModel = remember {
//        CameraViewModel()
//    }
//    val homeViewModel = remember {
//        HomeViewModel()
//    }
//    val detailViewModel = remember {
//        DetailsViewModel()
//    }
    NavHost(
    navController = navHostController,
    startDestination = "moviesearchscreen"
    ) {
        composable("moviesearchscreen"){
            MovieSearchScreen(moviesViewModel)
        }
        composable(route= "favorites"){
            FavoritesScreen(moviesViewModel)
        }

    }
}




