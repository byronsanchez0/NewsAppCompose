package com.example.movieappcompose

import com.google.gson.annotations.SerializedName

data class GuardianResponse(
    val response: GuardianData
)

data class GuardianData(
    val results: List<GuardianResult>
)

data class GuardianResult(
    val webTitle: String,
    val sectionName: String,
    val webUrl: String
)