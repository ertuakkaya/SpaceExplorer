package com.ertugrulakkaya.spaceexplorer.presentation.launch_list

import com.ertugrulakkaya.spaceexplorer.domain.usecase.ObserveLaunchesUseCase
import com.ertugrulakkaya.spaceexplorer.domain.usecase.RefreshLaunchesUseCase
import com.ertugrulakkaya.spaceexplorer.fakes.FakeLaunchRepository
import com.ertugrulakkaya.spaceexplorer.presentation.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HomeViewModelTest {

    // test dispatcher
    private val testDispatcher = StandardTestDispatcher()

    // ready dependencies
    private var fakeRepository = FakeLaunchRepository()
    private var observeLaunchesUseCase = ObserveLaunchesUseCase(fakeRepository)
    private var refreshLaunchesUseCase = RefreshLaunchesUseCase(fakeRepository)
    private lateinit var viewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        fakeRepository = FakeLaunchRepository()
        observeLaunchesUseCase = ObserveLaunchesUseCase(fakeRepository)
        refreshLaunchesUseCase = RefreshLaunchesUseCase(fakeRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `if data is fetched succesfully, launches state is should be Success`() = runTest {

        viewModel = HomeViewModel(observeLaunchesUseCase, refreshLaunchesUseCase)

        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertTrue(state.launches is UiState.Success, "Launches state should be Success")


    }

    @Test
    fun `if db is failed, launches state should be Error`() = runTest {
        fakeRepository.shouldThrowError = true

        viewModel = HomeViewModel(observeLaunchesUseCase, refreshLaunchesUseCase)

        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertTrue(state.launches is UiState.Error)

        val errorState = state.launches as UiState.Error

        assertEquals("An error occurred while loading launches", errorState.message)

    }


}