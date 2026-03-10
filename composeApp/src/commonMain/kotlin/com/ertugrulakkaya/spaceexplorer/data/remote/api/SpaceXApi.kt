package com.ertugrulakkaya.spaceexplorer.data.remote.api

import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LaunchDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.RocketDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SpaceXApi(
    private val client: HttpClient
) {
    suspend fun getLaunches(): List<LaunchDto> {
        return client
            .get("${ApiConstants.BASE_URL}/${ApiConstants.LAUNCHES_ENDPOINT}")
            .body()
    }

    suspend fun getRocket(
        rocketId: String
    ): RocketDto {
        return client
            .get("${ApiConstants.BASE_URL}/${ApiConstants.ROCKETS_ENDPOINT}/$rocketId")
            .body()
    }
}