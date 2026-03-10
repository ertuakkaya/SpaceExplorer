package com.ertugrulakkaya.spaceexplorer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rockets")
data class RocketEntity(

    @PrimaryKey
    val id: String,

    val name: String,
    val type: String,
    val active: Boolean,

    val stages: Int,
    val boosters: Int,

    val costPerLaunch: Int,
    val successRatePct: Int,

    val firstFlight: String,

    val country: String,
    val company: String,

    val description: String,
    val wikipedia: String,

    val heightMeters: Double?,
    val diameterMeters: Double?,
    val massKg: Int?,

    val enginesCount: Int,
    val engineType: String,
    val engineVersion: String,

    val firstStageEngines: Int,
    val secondStageEngines: Int
)