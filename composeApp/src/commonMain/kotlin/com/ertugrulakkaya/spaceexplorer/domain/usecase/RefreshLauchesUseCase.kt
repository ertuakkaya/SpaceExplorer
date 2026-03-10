package com.ertugrulakkaya.spaceexplorer.domain.usecase

import com.ertugrulakkaya.spaceexplorer.domain.repository.LaunchRepository

class RefreshLaunchesUseCase(
    private val repository: LaunchRepository
) : NoParamsUseCase<Result<Unit>>{

    override suspend fun invoke(): Result<Unit> {
       return repository.refreshLaunches()
    }
}