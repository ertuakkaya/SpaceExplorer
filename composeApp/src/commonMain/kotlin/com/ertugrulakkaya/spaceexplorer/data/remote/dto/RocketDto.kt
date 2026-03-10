package com.ertugrulakkaya.spaceexplorer.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RocketDto(
    val id: String,
    val name: String,
    val type: String,
    val active: Boolean,
    val stages: Int,
    val boosters: Int,
    val cost_per_launch: Int,
    val success_rate_pct: Int,
    val first_flight: String,
    val country: String,
    val company: String,
    val wikipedia: String,
    val description: String,
    val flickr_images: List<String>,
    val height: HeightDto,
    val diameter: DiameterDto,
    val mass: MassDto,
    val first_stage: FirstStageDto,
    val second_stage: SecondStageDto,
    val engines: EnginesDto,
    val landing_legs: LandingLegsDto,
    val payload_weights: List<PayloadWeightDto>
)

@Serializable
data class HeightDto(
    val meters: Double?,
    val feet: Double?
)

@Serializable
data class DiameterDto(
    val meters: Double?,
    val feet: Double?
)

@Serializable
data class MassDto(
    val kg: Int?,
    val lb: Int?
)

@Serializable
data class FirstStageDto(
    val reusable: Boolean,
    val engines: Int,
    val fuel_amount_tons: Double,
    val burn_time_sec: Int?,
    val thrust_sea_level: ThrustDto,
    val thrust_vacuum: ThrustDto
)

@Serializable
data class SecondStageDto(
    val reusable: Boolean,
    val engines: Int,
    val fuel_amount_tons: Double,
    val burn_time_sec: Int?,
    val thrust: ThrustDto
)

@Serializable
data class EnginesDto(
    val number: Int,
    val type: String,
    val version: String,
    val propellant_1: String,
    val propellant_2: String,
    val thrust_to_weight: Double
)

@Serializable
data class PayloadWeightDto(
    val id: String,
    val name: String,
    val kg: Int,
    val lb: Int
)

@Serializable
data class ThrustDto(
    val kN: Int?,
    val lbf: Int?
)

@Serializable
data class LandingLegsDto(
    val number: Int,
    val material: String?
)