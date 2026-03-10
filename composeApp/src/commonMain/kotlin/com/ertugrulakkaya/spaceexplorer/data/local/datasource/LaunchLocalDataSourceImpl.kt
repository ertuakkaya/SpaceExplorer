package com.ertugrulakkaya.spaceexplorer.data.local.datasource

import com.ertugrulakkaya.spaceexplorer.data.local.dao.LaunchDao
import com.ertugrulakkaya.spaceexplorer.data.local.entity.LaunchEntity
import kotlinx.coroutines.flow.Flow

class LaunchLocalDataSourceImpl(
    private val launchDao: LaunchDao
) : LaunchLocalDataSource {

    override fun getLaunches(): Flow<List<LaunchEntity>> {
        return launchDao.getAllLaunches()
    }

    override suspend fun saveLaunches(launches: List<LaunchEntity>) {
        launchDao.insertLaunches(launches)
    }

    override suspend fun clearAll() {
        launchDao.deleteAllLaunches()
    }
}