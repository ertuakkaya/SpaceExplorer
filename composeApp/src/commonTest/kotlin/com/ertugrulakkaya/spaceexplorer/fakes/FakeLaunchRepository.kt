package com.ertugrulakkaya.spaceexplorer.fakes

import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import com.ertugrulakkaya.spaceexplorer.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeLaunchRepository : LaunchRepository {
    var isRefreshCalled = false
    var resultToReturn : Result<Unit> = Result.success(Unit)
    var lauchesToReturn : Flow<List<Launch>> = flowOf(emptyList())
    var shouldThrowError = false

    override fun getLaunches(): Flow<List<Launch>> = flow {
        if (shouldThrowError) throw Exception("Test Error")
        lauchesToReturn.collect { emit(it) }
    }

    override suspend fun refreshLaunches(): Result<Unit> {
        isRefreshCalled = true
        return resultToReturn
    }

}