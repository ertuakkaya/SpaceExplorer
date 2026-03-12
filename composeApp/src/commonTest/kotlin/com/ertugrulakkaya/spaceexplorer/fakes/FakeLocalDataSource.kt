package com.ertugrulakkaya.spaceexplorer.fakes

import com.ertugrulakkaya.spaceexplorer.data.local.datasource.LaunchLocalDataSource
import com.ertugrulakkaya.spaceexplorer.data.local.entity.LaunchEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLocalDataSource : LaunchLocalDataSource {

    var savedEntities : List<LaunchEntity> = emptyList()
    var isSaveCalled = false

    override fun getLaunches(): Flow<List<LaunchEntity>> {
       return flowOf(savedEntities)
    }

    override suspend fun saveLaunches(launches: List<LaunchEntity>) {
        savedEntities = launches
        isSaveCalled = true
    }

    override suspend fun clearAll() {

    }
}