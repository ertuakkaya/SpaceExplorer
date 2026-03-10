package com.ertugrulakkaya.spaceexplorer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LaunchEntity(
    @PrimaryKey val id: String,
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