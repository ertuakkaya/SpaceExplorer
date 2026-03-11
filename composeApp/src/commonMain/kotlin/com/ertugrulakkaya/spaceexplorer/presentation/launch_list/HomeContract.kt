package com.ertugrulakkaya.spaceexplorer.presentation.launch_list

import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import com.ertugrulakkaya.spaceexplorer.presentation.base.UiState

data class HomeUiState(
    val launches: UiState<List<Launch>> = UiState.Idle,
    val isRefreshing: Boolean = false
)

sealed interface HomeEffect {
    data class NavigateToDetail(
        val missionName: String,
        val launchDate: String,
        val rocketName: String,
        val missionDescription: String,
        val launchSuccess: Boolean,
        val badgePhotoUrl : String,
        val articleUrl : String,
        val wikiUrl : String,
        val webCastUrl : String,
    ) : HomeEffect
}

sealed class HomeEvent {
    object RefreshLaunches : HomeEvent()
    object LoadLaunches : HomeEvent()

    data class OnLaunchClick(
        val missionName: String,
        val launchDate: String,
        val rocketName: String,
        val missionDescription: String,
        val launchSuccess: Boolean,
        val badgePhotoUrl : String,
        val articleUrl : String,
        val wikiUrl : String,
        val webCastUrl : String,
    ) : HomeEvent()

}






