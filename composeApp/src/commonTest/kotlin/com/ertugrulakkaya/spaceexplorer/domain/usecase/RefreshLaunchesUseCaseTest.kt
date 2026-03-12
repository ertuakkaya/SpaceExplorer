package com.ertugrulakkaya.spaceexplorer.domain.usecase

import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import com.ertugrulakkaya.spaceexplorer.domain.repository.LaunchRepository
import com.ertugrulakkaya.spaceexplorer.fakes.FakeLaunchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue



class RefreshLaunchesUseCaseTest {

    private val fakeRepository = FakeLaunchRepository()
    private val useCase = RefreshLaunchesUseCase(fakeRepository)

    @Test
    fun `invoke calls refreshLaunches on the repository`() = runTest {
        fakeRepository.isRefreshCalled = false

        useCase.invoke()

        assertTrue(fakeRepository.isRefreshCalled, "refreshLaunches was not called")

    }
}