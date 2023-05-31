package com.example.movieappcompose

import android.content.Context
import android.graphics.drawable.Icon
import android.preference.PreferenceManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesScreen(moviesViewModel: MoviesViewModel) {
    val context = LocalContext.current
    val id = getUserId(context)
    val delBtn by moviesViewModel.delBtn.collectAsState()
    moviesViewModel.getFavMovie(id.toLong())
    val movies by moviesViewModel.movies.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie ->
            Text("ESTA PELI TE GUTS: ${movie.title}")
            IconButton(onClick = { moviesViewModel.deleteFavMovie(movie) }) {
                Icon(
                    delBtn,
                    contentDescription = stringResource(R.string.start_playlist),
                    modifier = Modifier
                        .size(50.dp),
                    tint = MaterialTheme.colorScheme.inversePrimary
                )

            }
        }
    }

}
private fun getUserId(context: Context) : Int {
    val sharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE)
    return sharedPreferences.getInt("userId", 0)
}