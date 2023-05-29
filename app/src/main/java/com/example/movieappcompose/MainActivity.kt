package com.example.movieappcompose

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieappcompose.RetrofitClient.searchMovies
import com.example.movieappcompose.navigation.MainScreen
import com.example.movieappcompose.ui.theme.MovieAppComposeTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences


    //     val viewModel = MoviesViewModel()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppComposeTheme {
                val navController = rememberNavController()
//                val movies = MutableStateFlow(listOf<Movie>())
//                movies.asStateFlow()
//
//                LaunchedEffect(Unit) {
//                    val userService = RetrofitClient.movieService
//
//                    val retrievedUsers = withContext(Dispatchers.IO) {
//                        userService.getMovies()
//                    }
//                    movies.emit(retrievedUsers)
//                }
//                println(movies)
//
////                Scaffold {
////                    } //AAAAAAAAAQUI
//                MainScreen()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(
                            navController
                        )
                    }
                    composable("main") {
                        MainScreen()
                    }
                }

//                MovieSearchScreen()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchScreen() {
    var searchTerm by remember { mutableStateOf("") }
    var movies by remember { mutableStateOf<List<Movie>>(emptyList()) }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchTerm,
            onValueChange = { txt ->
                searchTerm = txt
            },
            label = { Text(text = "Enter a movie name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            onClick = {
                movies = runBlocking { searchMovies(searchTerm) }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Search")

        }
        movieList(movies = movies, {

        })


    }
}

@Composable
fun movieList(
    movies: List<Movie>,
    onSongClick: (Movie) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie ->
            MovieItem(movie, onSongClick)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MovieItem(
    movie: Movie,
    onClick: (Movie) -> Unit
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Details(sheetState = sheetState, movie, onDismiss = {
        scope.launch {
            sheetState.hide()
        }
    }
    )


    Row(modifier = Modifier
        .padding(16.dp)
        .clickable {
            scope.launch {

                sheetState.partialExpand()
            }
        }) {
        Image(
            painter = rememberAsyncImagePainter(movie.poster),
            contentDescription = "Movie Poster",
            modifier = Modifier.size(80.dp)
        )

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = movie.title)
//            Text(text = movie.released)
        }
    }
}


