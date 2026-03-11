package com.ertugrulakkaya.spaceexplorer.domain.model

import kotlin.time.Instant

data class Launch(
    val id: String,
    val name: String,
    val flightNumber: Int,
    val details: String?,
    val success: Boolean?,
    val dateUtc: Instant,
    val rocketName: String?,
    val patchImageSmall: String?,
    val patchImageBig: String?,
    val article: String?,
    val webcast : String?,
    val wikipedia: String?,
    val youtubeId: String?
)