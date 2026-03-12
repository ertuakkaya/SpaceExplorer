package com.ertugrulakkaya.spaceexplorer.presentation.launch_detail

data class DetailUiState(
    val missionName: String,
    val launchDate: String,
    val rocketName: String,
    val missionDescription: String,
    val rocketDescription : String,
    val launchSuccess: Boolean,
    val badgePhotoUrl : String,
    val articleUrl : String,
    val wikiUrl : String,
    val webCastUrl : String,
)

sealed interface DetailEffect {
    data object NavigateBack : DetailEffect

    data class OpenVideoUrl (val url : String) : DetailEffect

    data class OpenArticleUrl (val url : String) : DetailEffect

    data class OpenWikiUrl (val url : String) : DetailEffect
}

sealed class DetailEvent {
    data object OnBackClick : DetailEvent()

    data class OnWatchVideoClick(val url : String) : DetailEvent()

    data class OnReadArticleClick(val url : String) : DetailEvent()

    data class OnWikiClick(val url : String) : DetailEvent()

}






