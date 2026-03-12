package com.ertugrulakkaya.spaceexplorer.fakes

import com.ertugrulakkaya.spaceexplorer.data.remote.datasource.LaunchRemoteDataSource
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.DiameterDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.EnginesDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.FirstStageDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.HeightDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LandingLegsDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LaunchDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.MassDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.PayloadWeightDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.RocketDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.SecondStageDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.ThrustDto

class FakeRemoteDataSource : LaunchRemoteDataSource {

    var launchesToReturn: Result<List<LaunchDto>> = Result.success(emptyList())
    var rocketToReturn: Result<RocketDto> = Result.success(
        RocketDto(
            id = "r1",
            name = "Falcon Test",
            description = "Test rocket description",
            type = "Falcon 9",
            active = true,
            stages = 3,
            boosters = 1,
            cost_per_launch = 9999,
            success_rate_pct = 45,
            first_flight = "2020-01-01",
            country = "USA",
            company = "SpaceX",
            wikipedia = "https://en.wikipedia.org/wiki/Falcon_9",
            flickr_images = listOf(
                "https://imgur.com/DaCfMsj.jpg"
            ),
            height = HeightDto(
                meters = 70.0,
                feet = 229.6
            ),
            diameter = DiameterDto(
                meters = 3.7,
                feet = 12.0
            ),
            mass = MassDto(
                kg = 549054,
                lb = 1207920
            ),
            first_stage = FirstStageDto(
                thrust_sea_level = ThrustDto(kN = 7607, lbf = 1710000),
                thrust_vacuum = ThrustDto(kN = 8227, lbf = 1849500),
                reusable = true,
                engines = 9,
                fuel_amount_tons = 385.0,
                burn_time_sec = 162
            ),
            second_stage = SecondStageDto(
                thrust = ThrustDto(kN = 934, lbf = 210000),
                reusable = false,
                engines = 1,
                fuel_amount_tons = 90.0,
                burn_time_sec = 397
            ),
            engines = EnginesDto(
                number = 9,
                type = "merlin",
                version = "1D+",
                propellant_1 = "liquid oxygen",
                propellant_2 = "RP-1 kerosene",
                thrust_to_weight = 180.1
            ),
            landing_legs = LandingLegsDto(
                number = 4,
                material = "carbon fiber"
            ),
            payload_weights = listOf(
                PayloadWeightDto(
                    id = "leo",
                    name = "Low Earth Orbit",
                    kg = 22800,
                    lb = 50265
                )
            )
        )
    )

    override suspend fun getLaunches(): Result<List<LaunchDto>> {
        return launchesToReturn
    }

    override suspend fun getRocket(rocketId: String): Result<RocketDto> {
       return rocketToReturn
    }
}