package com.ertugrulakkaya.spaceexplorer.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ertugrulakkaya.spaceexplorer.Greeting
import com.ertugrulakkaya.spaceexplorer.presentation.base.UiState
import com.ertugrulakkaya.spaceexplorer.presentation.home.components.LaunchCard
import com.ertugrulakkaya.spaceexplorer.presentation.navigation.Screen
import com.ertugrulakkaya.spaceexplorer.presentation.util.toDate
import com.ertugrulakkaya.spaceexplorer.presentation.util.toFullDateTime
import io.ktor.websocket.Frame
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToDetail: (Screen.Detail) -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(effect) {
        viewModel.effect.collect { effect ->
            when(effect){
                is HomeEffect.NavigateToDetail -> onNavigateToDetail(
                    Screen.Detail(
                        missionName = effect.missionName,
                        launchDate = effect.launchDate,
                        rocketName = effect.rocketName,
                        missionDescription = effect.missionDescription,
                        launchSuccess = effect.launchSuccess,
                        badgePhotoUrl = effect.badgePhotoUrl,
                        articleUrl = effect.articleUrl,
                        webCastUrl = effect.webCastUrl,
                        wikiUrl = effect.wikiUrl
                    )

                )
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                title = {
                    Text(
                        text = "SpaceX Launches 1",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize

                        )
                    )
                }
            )
        }
    ) { paddingValues ->
        when (val launchesState = uiState.launches) {
            is UiState.Error -> {
                Text(
                    text = launchesState.message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            UiState.Idle -> {

            }

            UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success -> {
                val launches = launchesState.data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)

                ) {
                    items(
                        items = launches,
                        key = { launch -> launch.id }
                    ) { launch ->
                        LaunchCard(
                            modifier = Modifier,
                            launch = launch,
                            onClick = {
                                viewModel.onEvent(
                                    HomeEvent.OnLaunchClick(
                                        missionName = launch.name,
                                        launchDate = launch.dateUtc.toFullDateTime(),
                                        rocketName = launch.rocketName ?: "Unknown",
                                        missionDescription = launch.details ?: "No description available",
                                        launchSuccess = launch.success ?: false,
                                        badgePhotoUrl = launch.patchImageSmall ?: "",
                                        articleUrl = launch.article ?: "",
                                        wikiUrl = launch.wikipedia ?: "",
                                        webCastUrl = launch.webcast ?: ""
                                    )
                                )
                            }

                        )
                    }
                }
            }
        }
    }

}