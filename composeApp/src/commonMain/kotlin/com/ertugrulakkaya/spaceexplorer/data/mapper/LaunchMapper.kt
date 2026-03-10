package com.ertugrulakkaya.spaceexplorer.data.mapper

import com.ertugrulakkaya.spaceexplorer.data.local.entity.LaunchEntity
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LaunchDto
import com.ertugrulakkaya.spaceexplorer.domain.model.Launch

fun LaunchDto.toDomain(
    rocketName: String?
): Launch {
    return Launch(
        id = id,
        name = name,
        flightNumber = flightNumber,
        details = details,
        success = success,
        dateUtc = dateUtc,
        rocketName = rocketName,
        patchImageSmall = links.patch?.small,
        article = links.article,
        wikipedia = links.wikipedia,
        youtubeId = links.youtubeId
    )
}


fun LaunchDto.toEntity(
    rocketName: String?
): LaunchEntity {
    return LaunchEntity(
        id = id,
        name = name,
        flightNumber = flightNumber,
        details = details,
        success = success,
        dateUtc = dateUtc,
        rocketName = rocketName,
        patchImageSmall = links.patch?.small,
        article = links.article,
        wikipedia = links.wikipedia,
        youtubeId = links.youtubeId
    )
}

fun LaunchEntity.toDomain(): Launch {
    return Launch(
        id = id,
        name = name,
        flightNumber = flightNumber,
        details = details,
        success = success,
        dateUtc = dateUtc,
        rocketName = rocketName,
        patchImageSmall = patchImageSmall,
        article = article,
        wikipedia = wikipedia,
        youtubeId = youtubeId
    )
}

