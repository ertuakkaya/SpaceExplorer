package com.ertugrulakkaya.spaceexplorer.data.remote.datasource

import com.ertugrulakkaya.spaceexplorer.data.remote.api.ApiConstants
import com.ertugrulakkaya.spaceexplorer.data.remote.api.SpaceXApi
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LaunchDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.RocketDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class LaunchRemoteDataSourceImpl(
    private val api: SpaceXApi
) : LaunchRemoteDataSource {

    override suspend fun getLaunches(): Result<List<LaunchDto>> {
        return try {
            val launches = api.getLaunches()
             Result.success(launches)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRocket(rocketId: String): Result<RocketDto> {
        return try {
            val rocket = api.getRocket(rocketId)
            Result.success(rocket)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}