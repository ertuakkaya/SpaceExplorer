package com.ertugrulakkaya.spaceexplorer.data.local.datasource

import com.ertugrulakkaya.spaceexplorer.data.local.dao.LaunchDao
import com.ertugrulakkaya.spaceexplorer.data.local.entity.LaunchEntity
import kotlinx.coroutines.flow.Flow

interface LaunchLocalDataSource{

    fun getLaunches(): Flow<List<LaunchEntity>>

    suspend fun saveLaunches(launches: List<LaunchEntity>)

    suspend fun clearAll()

}