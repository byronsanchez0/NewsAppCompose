package com.example.movieappcompose

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.movieappcompose.entity.FavMovie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesScreen(moviesViewModel: MoviesViewModel) {
    val context = LocalContext.current
    val id = getUserId(context)

    moviesViewModel.getFavMovie(id.toLong())
    val movies by moviesViewModel.movies.collectAsState()
    val pagerState = rememberPagerState()
    var currentPage by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        HorizontalPager(
            pageCount = movies.size,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            val item = movies[pageIndex]
            ItemCard(movie = item, moviesViewModel)

        }


        // Indicator dots for pagination

    }
}

//    HorizontalPager(modifier = Modifier.fillMaxSize(), pageCount = movies.size) {
//        movies.forEach { movie ->
//            Column(modifier = Modifier.fillMaxSize()) {
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(movie.poster)
//                        .crossfade(true)
//                        .scale(Scale.FILL)
//                        .build(), contentDescription = null
//                )
////                Image(
////                    painter = rememberImagePainter(movie.poster),
////                    contentDescription = movie.title,
////                    modifier = Modifier.size(300.dp)
////                )
//                IconButton(onClick = { moviesViewModel.deleteFavMovie(movie) }) {
//                    Icon(
//                        delBtn,
//                        contentDescription = stringResource(R.string.start_playlist),
//                        modifier = Modifier
//                            .size(50.dp),
//                        tint = MaterialTheme.colorScheme.inversePrimary
//                    )
//
//                }
//            }
//        }
//    }



//    HorizontalPager(
//        modifier = Modifier.fillMaxSize(),
//        pageCount = movies.size
//    ) { page ->
//        Box(
//            modifier = Modifier
//                .padding(10.dp)
//                .background(Color.Blue)
//                .fillMaxWidth()
//                .aspectRatio(1f),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text = page.toString(), fontSize = 32.sp)
//        }
//    }

//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//
//        items(movies) { movie ->
//            Text("ESTA PELI TE GUTS: ${movie.title}")
//            IconButton(onClick = { moviesViewModel.deleteFavMovie(movie) }) {
//                Icon(
//                    delBtn,
//                    contentDescription = stringResource(R.string.start_playlist),
//                    modifier = Modifier
//                        .size(50.dp),
//                    tint = MaterialTheme.colorScheme.inversePrimary
//                )
//
//            }
//        }
//    }





@Composable
fun ItemCard(movie: FavMovie, moviesViewModel: MoviesViewModel) {
    val delBtn by moviesViewModel.delBtn.collectAsState()
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(movie.poster),

            contentDescription = "Movie Poster",
            modifier = Modifier.size(80.dp)
        )
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

//@Composable
//fun HorizontalPagesWithImageList(images: List<Int>) {
//    var currentPage by remember { mutableStateOf(0) }
//    val transition = updateTransition(currentPage, label = "currentPageTransition")
//    val offset by transition.animateOffset(
//        transitionSpec = {
//            tween(durationMillis = 300, easing = LinearOutSlowInEasing)
//        }
//    ) { page ->
//        page * -200f
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 16.dp)
//    ) {
//        LazyRow(
//            modifier = Modifier
//                .fillMaxSize()
//                .align(Alignment.Center)
//                .offset { IntOffset(offset.toInt(), 0) }
//                .animateContentSize(),
//            contentPadding = PaddingValues(horizontal = 8.dp)
//        ) {
//            items(images) { image ->
//                Image(
//                    painter = painterResource(id = image),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(200.dp)
//                        .clip(MaterialTheme.shapes.medium)
//                )
//            }
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter)
//                .padding(bottom = 16.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            images.forEachIndexed { index, _ ->
//                PageIndicator(index == currentPage)
//            }
//        }
//    }
//}

private fun getUserId(context: Context): Int {
    val sharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE)
    return sharedPreferences.getInt("userId", 0)
}