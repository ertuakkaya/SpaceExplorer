package com.ertugrulakkaya.spaceexplorer.presentation.home

import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import com.ertugrulakkaya.spaceexplorer.presentation.base.UiState

data class HomeUiState(
    val launches: UiState<List<Launch>> = UiState.Idle,
    val isRefreshing: Boolean = false
)

sealed interface HomeEffect {
    data class NavigateToDetail(val launchId: String) : HomeEffect
}

sealed class HomeEvent {
    object RefreshLaunches : HomeEvent()
    object LoadLaunches : HomeEvent()

    data class OnLaunchClick(val launchId: String) : HomeEvent()

}






