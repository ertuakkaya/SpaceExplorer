package com.ertugrulakkaya.spaceexplorer.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    secondary = ContainerColor,
    tertiary = ContainerBorderColor,
    onPrimary = TextColor

)


@Composable
fun SpaceExplorerTheme(
    content : @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}