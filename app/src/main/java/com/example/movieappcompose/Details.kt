package com.example.movieappcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Details(
    sheetState: SheetState,
    movie: Movie,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberModalBottomSheetState()

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
                    Text(text = movie.title)
//            Text(text = movie.released)
                }
            }
        }
    }
}
//
//    ModalBottomSheetLayout(scaffoldState = scaffoldState, sheetPeekHeight = 128.dp, sheetContent = {
//        Box(
//            Modifier
//                .fillMaxWidth()
//                .height(128.dp), contentAlignment = Alignment.Center
//        ) {
//            Text("Swipe up to expand sheet")
//        }
//        Column(
//            Modifier
//                .fillMaxWidth()
//                .padding(64.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Sheet content")
//            Spacer(Modifier.height(20.dp))
//            Button(onClick = {
//                scope.launch { scaffoldState.bottomSheetState.show() }
//            }) {
//                Text("Click to collapse sheet")
//            }
//        }
//    }) { innerPadding ->
//        Box(Modifier.padding(innerPadding)) {
//            Text("Scaffold Content")
//        }
//    }



//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun OpenBottomSheetButton() {
//    val scaffoldState = rememberBottomSheetScaffoldState()
//    Button(
//        onClick = { scaffoldState.show() }, modifier = Modifier.padding(16.dp)
//    ) {
//        Text("Open BottomSheet")
//    }
//}