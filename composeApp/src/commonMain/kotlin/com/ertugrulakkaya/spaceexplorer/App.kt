package com.ertugrulakkaya.spaceexplorer

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.DetailScreen
import com.ertugrulakkaya.spaceexplorer.presentation.launch_list.HomeScreen
import com.ertugrulakkaya.spaceexplorer.presentation.navigation.Screen
import com.ertugrulakkaya.spaceexplorer.presentation.theme.SpaceExplorerTheme

@Composable
@Preview
fun App() {

    val navController = rememberNavController()



    SpaceExplorerTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Home
        ) {
            composable<Screen.Home> {
                HomeScreen(
                    onNavigateToDetail = { detailData ->
                        navController.navigate(
                            detailData
                        ){
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<Screen.Detail> { backStackEntry ->
                val detailArgs = backStackEntry.toRoute<Screen.Detail>()
                DetailScreen(
                    onNavigateToDetail = {

                    }
                )
            }
        }


    }
}