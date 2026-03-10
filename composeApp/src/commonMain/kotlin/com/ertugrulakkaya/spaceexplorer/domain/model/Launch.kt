package com.ertugrulakkaya.spaceexplorer.domain.model

data class Launch(
    val id: String,
    val name: String,
    val flightNumber: Int,
    val details: String?,
    val success: Boolean?,
    val dateUtc: String,
    val rocketName: String?,
    val patchImageSmall: String?,
    val article: String?,
    val wikipedia: String?,
    val youtubeId: String?
)