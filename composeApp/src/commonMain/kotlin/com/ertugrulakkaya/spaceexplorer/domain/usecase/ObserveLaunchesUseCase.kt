package com.ertugrulakkaya.spaceexplorer.domain.usecase

import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import com.ertugrulakkaya.spaceexplorer.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow

class ObserveLaunchesUseCase(
    private val repository: LaunchRepository
) : NoParamsUseCase<Flow<List<Launch>>>{
    override suspend fun invoke(): Flow<List<Launch>> {
        return repository.getLaunches()
    }
}