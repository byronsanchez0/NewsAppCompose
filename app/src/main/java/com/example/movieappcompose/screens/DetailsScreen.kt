package com.example.movieappcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.movieappcompose.Movie


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(
    sheetState: SheetState,
    movie: Movie,
    onDismiss: () -> Unit
) {
    when {
        sheetState.isVisible -> {
            ModalBottomSheet(
                modifier = Modifier.fillMaxSize(),
                onDismissRequest = onDismiss,
                sheetState = sheetState
            ) {
                Image(
                    painter = rememberAsyncImagePainter(movie.poster),
                    contentDescription = "Movie Poster",
                    modifier = Modifier.size(80.dp)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(text = movie.title, color = MaterialTheme.colorScheme.background,  style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
