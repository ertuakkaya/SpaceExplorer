package com.ertugrulakkaya.spaceexplorer.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
         @Serializable
         data object Home : Screen()

         @Serializable
         data class Detail(val id: String) : Screen()

}