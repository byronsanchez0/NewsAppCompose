package com.example.movieappcompose

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

import io.ktor.client.request.get

object KtorClient {

    private val client = HttpClient {
        install(JsonFeature)
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    suspend fun searchNews(query: String): List<NewsArticle> {
        val apiKey = "YOUR_API_KEY" // Replace with your own API key
        val url = "https://content.guardianapis.com/search?q=$query&api-key=$apiKey"

        return client.get<GuardianResponse>(url).response.results.map { result ->
            NewsArticle(result.webTitle, result.sectionName, result.webUrl)
        }
    }
}