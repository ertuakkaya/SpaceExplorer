package com.ertugrulakkaya.spaceexplorer.data.remote.dto

import kotlinx.serialization.SerialName
import  kotlinx.serialization.Serializable

@Serializable
data class LaunchDto(
    val id: String,
    val name: String,
    val rocket: String,

    @SerialName("flight_number")
    val flightNumber: Int,

    val details: String?,

    val success: Boolean?,

    @SerialName("date_utc")
    val dateUtc: String,

    val links: LinksDto
)

@Serializable
data class LinksDto(
    val patch: PatchDto?,

    val article: String?,

    val wikipedia: String?,

    @SerialName("youtube_id")
    val youtubeId: String?,

    val webcast : String?
)

@Serializable
data class PatchDto(
    val small: String?,
    val large: String?
)