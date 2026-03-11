package com.ertugrulakkaya.spaceexplorer.presentation.launch_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ertugrulakkaya.spaceexplorer.domain.usecase.ObserveLaunchesUseCase
import com.ertugrulakkaya.spaceexplorer.domain.usecase.RefreshLaunchesUseCase
import com.ertugrulakkaya.spaceexplorer.presentation.base.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                sendEffect(
                    HomeEffect.NavigateToDetail(
                        missionName = event.missionName,
                        launchDate = event.launchDate,
                        rocketName = event.rocketName,
                        missionDescription = event.missionDescription,
                        launchSuccess = event.launchSuccess,
                        badgePhotoUrl = event.badgePhotoUrl,
                        articleUrl = event.articleUrl,
                        wikiUrl = event.wikiUrl,
                        webCastUrl = event.webCastUrl
                    )
                )
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
            try {
                observeLaunchesUseCase().collect { launches ->
                    _uiState.update {
                        it.copy(
                            launches = UiState.Success(launches),
                            isRefreshing = false
                        )
                    }
                }
            }catch (e : Exception){
                _uiState.update {
                    it.copy(
                        launches = UiState.Error("An error occurred while loading launches"),
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
                    _uiState.update {
                        it.copy(
                            isRefreshing = false,
                            launches = UiState.Error("An error occurred while refreshing launches")
                        )
                    }
                }
            )

        }
    }


}