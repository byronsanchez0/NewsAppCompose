package com.example.movieappcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.KtorClient.searchNews

@Composable
fun NewsSearchScreen() {
    var query by remember { mutableStateOf("") }
    var newsArticles by remember { mutableStateOf(emptyList<NewsArticle>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search news") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // Call the searchNews function to retrieve news articles
                // You might want to perform error handling and loading state management as well
                // For simplicity, we'll perform the search directly here
                LaunchedEffect(query) {
                    newsArticles = searchNews(query)
                }
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.End)
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (newsArticles.isNotEmpty()) {
            Text("Results:")
            Spacer(modifier = Modifier.height(8.dp))

            // Display the news articles
            for (article in newsArticles) {
                Text(
                    text = article.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = article.section,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewNewsSearchApp() {
    NewsSearchApp()
}
