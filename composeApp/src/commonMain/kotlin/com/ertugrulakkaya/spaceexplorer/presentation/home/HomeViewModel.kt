package com.ertugrulakkaya.spaceexplorer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ertugrulakkaya.spaceexplorer.domain.usecase.ObserveLaunchesUseCase
import com.ertugrulakkaya.spaceexplorer.domain.usecase.RefreshLaunchesUseCase
import com.ertugrulakkaya.spaceexplorer.presentation.base.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observeLaunchesUseCase: ObserveLaunchesUseCase,
    private val refreshLaunchesUseCase: RefreshLaunchesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    private val _effect = Channel<HomeEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()


    init {
        onEvent(HomeEvent.LoadLaunches)
        onEvent(HomeEvent.RefreshLaunches)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadLaunches -> loadLaunches()
            HomeEvent.RefreshLaunches -> refreshLaunches()
            is HomeEvent.OnLaunchClick -> {
                sendEffect(HomeEffect.NavigateToDetail(event.launchId))
            }

        }
    }

    private fun sendEffect(effect: HomeEffect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    private fun loadLaunches() {
        viewModelScope.launch {
            _uiState.update { it.copy(launches = UiState.Loading) }
            observeLaunchesUseCase().collect { launches ->
                _uiState.update {
                    it.copy(
                        launches = UiState.Success(launches),
                        isRefreshing = false
                    )
                }
            }
        }
    }

    private fun refreshLaunches() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            refreshLaunchesUseCase.invoke().fold(
                onSuccess = {
                    _uiState.update { it.copy(isRefreshing = false) }
                },
                onFailure = {

                }
            )

        }
    }


}