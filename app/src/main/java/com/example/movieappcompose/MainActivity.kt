package com.example.movieappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.movieappcompose.RetrofitClient.searchMovies
import com.example.movieappcompose.ui.theme.MovieAppComposeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

//     val viewModel = MoviesViewModel()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppComposeTheme {
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
                MovieSearchScreen()

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
                searchTerm = txt },
            label = { Text(text = "Enter a movie name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            onClick = {
                movies= runBlocking { searchMovies(searchTerm) }  },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Search")

        }
        movieList(movies = movies)


    }
}

@Composable
fun movieList(movies: List<Movie>){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Row(modifier = Modifier.padding(16.dp)) {
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


