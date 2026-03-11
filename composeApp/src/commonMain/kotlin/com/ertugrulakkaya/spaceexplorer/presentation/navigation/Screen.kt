package com.ertugrulakkaya.spaceexplorer.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data class Detail(
        val missionName: String,
        val launchDate: String,
        val rocketName: String,
        val missionDescription: String,
        val launchSuccess: Boolean,
        val badgePhotoUrl : String,
        val articleUrl : String,
        val wikiUrl : String,
        val webCastUrl : String,
    ) : Screen()

}