package com.ertugrulakkaya.spaceexplorer.data.remote.datasource

import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LaunchDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.RocketDto
import kotlinx.coroutines.flow.Flow

interface LaunchRemoteDataSource {

    suspend fun getLaunches(): Result<List<LaunchDto>>

    suspend fun getRocket(
        rocketId: String
    ): Result<RocketDto>

}