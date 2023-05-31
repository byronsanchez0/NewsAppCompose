package com.example.movieappcompose.navigation

import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieappcompose.Details
import com.example.movieappcompose.FavoritesScreen
import com.example.movieappcompose.MovieItem
import com.example.movieappcompose.MovieSearchScreen
import com.example.movieappcompose.MoviesViewModel
import com.example.movieappcompose.repo.FavRepo

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




