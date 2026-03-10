package com.ertugrulakkaya.spaceexplorer.data.mapper

import com.ertugrulakkaya.spaceexplorer.data.local.entity.RocketEntity
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.RocketDto
import com.ertugrulakkaya.spaceexplorer.domain.model.Rocket

fun RocketDto.toEntity(): RocketEntity {
    return RocketEntity(
        id = id,
        name = name,
        type = type,
        active = active,
        stages = stages,
        boosters = boosters,
        costPerLaunch = cost_per_launch,
        successRatePct = success_rate_pct,
        firstFlight = first_flight,
        country = country,
        company = company,
        description = description,
        wikipedia = wikipedia,
        heightMeters = height.meters,
        diameterMeters = diameter.meters,
        massKg = mass.kg,
        enginesCount = engines.number,
        engineType = engines.type,
        engineVersion = engines.version,
        firstStageEngines = first_stage.engines,
        secondStageEngines = second_stage.engines
    )
}

fun RocketDto.toDomain(): Rocket {
    return Rocket(
        id = id,
        name = name,
        country = country,
        company = company,
        firstFlight = first_flight,
        successRate = success_rate_pct,
        costPerLaunch = cost_per_launch,
        heightMeters = height.meters,
        diameterMeters = diameter.meters,
        massKg = mass.kg,
        engineCount = engines.number,
        engineType = engines.type,
        description = description,
        wikipedia = wikipedia,
        images = flickr_images
    )
}