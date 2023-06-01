package com.example.movieappcompose

import android.content.Context
import android.os.Bundle
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import coil.compose.rememberAsyncImagePainter
import com.example.movieappcompose.RetrofitClient.searchMovies
import com.example.movieappcompose.database.FavDataBase
import com.example.movieappcompose.screens.login.LoginScreen
import com.example.movieappcompose.screens.login.LoginViewModel
import com.example.movieappcompose.navigation.MainScreen
import com.example.movieappcompose.repo.FavRepo
import com.example.movieappcompose.screens.Details
import com.example.movieappcompose.screens.signup.SignUp
import com.example.movieappcompose.screens.signup.SignUpViewModel
import com.example.movieappcompose.ui.theme.MovieAppComposeTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = this.getSharedPreferences("sp", Context.MODE_PRIVATE)
        val id = sharedPreferences.getInt("userId", 0)

        val db = Room.databaseBuilder(
            this,
            FavDataBase::class.java, FavDataBase.DATABASE_NAME
        ).build()
        val favMoviesRepo = FavRepo(db.favMoviesDao())
        var startDestination = "login"
        if (id != 0) {
            startDestination = "mainscreen"
        }
        val context = this

        setContent {
            val viewModel = MoviesViewModel(favMoviesRepo)
            MovieAppComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = startDestination) {
                    composable("login") {
                        val viewModel = LoginViewModel(context)
                        LoginScreen(navController, viewModel)
                    }
                    composable("signup") {
                        val viewModel = SignUpViewModel(context)
                        SignUp(navController, viewModel)
                    }
                    composable("mainscreen") {
                        MainScreen(navController, viewModel)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchScreen(viewModel: MoviesViewModel) {
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
            Text(
                text = "Search",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.background
            )
        }
        movieList(movies = movies, viewModel)
    }
}

@Composable
fun movieList(
    movies: List<Movie>,
    viewModel: MoviesViewModel
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie ->
            MovieItem(movie, viewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MovieItem(
    movie: Movie,
    viewModel: MoviesViewModel
) {
    val favBtn by viewModel.favBtn.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
            Text(text = movie.title, color = MaterialTheme.colorScheme.background)
        }
        IconButton(onClick = { viewModel.addtoFavMovie(movie, getUserId(context)) }) {
            Icon(
                favBtn,
                contentDescription = stringResource(R.string.start_playlist),
                modifier = Modifier
                    .size(50.dp),
                tint = MaterialTheme.colorScheme.inversePrimary
            )
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar {
                    Text(
                        text = "anndido",
                        color = MaterialTheme.colorScheme.background,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

private fun getUserId(context: Context): Long {
    val sharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE)
    return sharedPreferences.getInt("userId", 0).toLong()
}
