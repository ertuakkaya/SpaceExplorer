package com.ertugrulakkaya.spaceexplorer.domain.repository

import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {

    fun getLaunches(): Flow<List<Launch>>

    suspend fun refreshLaunches(): Result<Unit>
}