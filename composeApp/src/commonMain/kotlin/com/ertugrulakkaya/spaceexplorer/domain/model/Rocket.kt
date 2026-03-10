package com.ertugrulakkaya.spaceexplorer.domain.model

data class Rocket(

    val id: String,
    val name: String,
    val country: String,
    val company: String,

    val firstFlight: String,
    val successRate: Int,

    val costPerLaunch: Int,

    val heightMeters: Double?,
    val diameterMeters: Double?,
    val massKg: Int?,

    val engineCount: Int,
    val engineType: String,

    val description: String,
    val wikipedia: String,

    val images: List<String>
)