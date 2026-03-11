package com.ertugrulakkaya.spaceexplorer.data.mapper

import com.ertugrulakkaya.spaceexplorer.data.local.entity.LaunchEntity
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LaunchDto
import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import kotlin.time.Instant

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
        patchImageBig = links.patch?.large,
        article = links.article,
        wikipedia = links.wikipedia,
        webcast = links.webcast,
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
        patchImageBig  = links.patch?.large,
        article = links.article,
        webcast = links.webcast,
        wikipedia = links.wikipedia,
        youtubeId = links.youtubeId,

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
        patchImageBig = patchImageBig,
        article = article,
        webcast = webcast,
        wikipedia = wikipedia,
        youtubeId = youtubeId
    )
}

