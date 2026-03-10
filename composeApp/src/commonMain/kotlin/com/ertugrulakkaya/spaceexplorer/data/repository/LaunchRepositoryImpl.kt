package com.ertugrulakkaya.spaceexplorer.data.repository

import com.ertugrulakkaya.spaceexplorer.data.local.datasource.LaunchLocalDataSource
import com.ertugrulakkaya.spaceexplorer.data.mapper.toDomain
import com.ertugrulakkaya.spaceexplorer.data.mapper.toEntity
import com.ertugrulakkaya.spaceexplorer.data.remote.datasource.LaunchRemoteDataSource
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.RocketDto
import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import com.ertugrulakkaya.spaceexplorer.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LaunchRepositoryImpl (
    private val launchLocalDataSource: LaunchLocalDataSource,
    private val launchRemoteDataSource: LaunchRemoteDataSource
) : LaunchRepository{

    override fun getLaunches(): Flow<List<Launch>> {
        return launchLocalDataSource.getLaunches()
            .map { entities ->
                entities.map { it.toDomain() }
            }

    }


    override suspend fun refreshLaunches(): Result<Unit> {
        return launchRemoteDataSource.getLaunches().fold(
            onSuccess = { remoteLaunches ->
                try {
                    val rocketCache = mutableMapOf<String, RocketDto>()

                   val entities = remoteLaunches.map { lauch ->
                       val rocket = rocketCache.getOrPut(lauch.rocket){
                           launchRemoteDataSource.getRocket(lauch.rocket).getOrThrow()
                       }
                       lauch.toEntity(rocketName = rocket.name)
                   }
                    launchLocalDataSource.saveLaunches(entities)
                    Result.success(Unit)
                } catch (e: Exception) {
                    Result.failure(e)
                }
            },
            onFailure = { throwable ->
                Result.failure(throwable)
            }
        )
    }
}