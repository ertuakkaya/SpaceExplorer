package com.ertugrulakkaya.spaceexplorer.data.repository

import com.ertugrulakkaya.spaceexplorer.data.local.datasource.LaunchLocalDataSource
import com.ertugrulakkaya.spaceexplorer.data.mapper.toDomain
import com.ertugrulakkaya.spaceexplorer.data.mapper.toEntity
import com.ertugrulakkaya.spaceexplorer.data.remote.datasource.LaunchRemoteDataSource
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.RocketDto
import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import com.ertugrulakkaya.spaceexplorer.domain.repository.LaunchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LaunchRepositoryImpl (
    private val launchLocalDataSource: LaunchLocalDataSource,
    private val launchRemoteDataSource: LaunchRemoteDataSource
) : LaunchRepository{
    // UI always gets the data from the database
    override fun getLaunches(): Flow<List<Launch>> {
        return launchLocalDataSource.getLaunches()
            .map { entities ->
                entities.map { it.toDomain() }
            }
            .flowOn(Dispatchers.Default)

    }


    // refresh db with new remote data
    override suspend fun refreshLaunches(): Result<Unit>  = withContext(Dispatchers.IO){
        launchRemoteDataSource.getLaunches().fold(
            onSuccess = { remoteLaunches ->
                try {
                    val rocketCache = mutableMapOf<String, RocketDto>()
                    // if map contains rocket do not request to remote. if not contains, request to remote and put to map
                   val entities = remoteLaunches.map { lauch ->
                       val rocket = rocketCache.getOrPut(lauch.rocket){
                           launchRemoteDataSource.getRocket(lauch.rocket).getOrThrow()
                       }
                       lauch.toEntity(rocketName = rocket.name,rocketDescription = rocket.description)
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