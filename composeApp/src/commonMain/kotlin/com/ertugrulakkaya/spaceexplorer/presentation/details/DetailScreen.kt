package com.ertugrulakkaya.spaceexplorer.presentation.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ertugrulakkaya.spaceexplorer.Greeting
import com.ertugrulakkaya.spaceexplorer.domain.util.openUrl
import com.ertugrulakkaya.spaceexplorer.presentation.base.UiState
import com.ertugrulakkaya.spaceexplorer.presentation.details.components.BorderedLinkButton
import com.ertugrulakkaya.spaceexplorer.presentation.details.components.DetailPhoto
import com.ertugrulakkaya.spaceexplorer.presentation.details.components.LaunchDate
import com.ertugrulakkaya.spaceexplorer.presentation.details.components.LaunchDescription
import com.ertugrulakkaya.spaceexplorer.presentation.details.components.TitleAndSubtitleWithBadge
import com.ertugrulakkaya.spaceexplorer.presentation.details.components.WatchVideoButton
import com.ertugrulakkaya.spaceexplorer.presentation.home.HomeEffect
import com.ertugrulakkaya.spaceexplorer.presentation.home.HomeEvent
import com.ertugrulakkaya.spaceexplorer.presentation.home.components.LaunchCard
import com.ertugrulakkaya.spaceexplorer.presentation.navigation.Screen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.article
import spaceexplorer.composeapp.generated.resources.book_ribbon
import spaceexplorer.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel : DetailViewModel = koinViewModel(),
    onNavigateToDetail: () -> Unit = {},
){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(effect) {
        viewModel.effect.collect { effect ->
            when(effect){
                DetailEffect.NavigateBack -> onNavigateToDetail()
                is DetailEffect.OpenArticleUrl -> openUrl(effect.url)
                is DetailEffect.OpenVideoUrl -> openUrl(effect.url)
                is DetailEffect.OpenWikiUrl -> openUrl(effect.url)
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
                        text = "SpaceX Launches",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize

                        )
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            DetailPhoto(
                modifier = Modifier
                    .height(350.dp),

                photoUrl = uiState.badgePhotoUrl
            )

            Spacer(modifier = Modifier.height(24.dp))

            TitleAndSubtitleWithBadge(
                modifier = Modifier.fillMaxWidth(),
                missionName = uiState.missionName,
                rocketName = uiState.rocketName,
                isLaunchSuccess = uiState.launchSuccess
            )

            Spacer(modifier = Modifier.height(32.dp))

            LaunchDate(launchDateTime = uiState.launchDate)

            Spacer(modifier = Modifier.height(32.dp))

            LaunchDescription(description = uiState.missionDescription)

            Spacer(modifier = Modifier.height(40.dp))

            WatchVideoButton(

                onClick = { viewModel.onEvent(DetailEvent.OnWatchVideoClick(uiState.webCastUrl)) },
                //buttonText = uiState.webCastUrl
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                BorderedLinkButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    onClick = {
                       viewModel.onEvent(DetailEvent.OnWikiClick(uiState.wikiUrl))
                    },
                    paninterResource = painterResource(Res.drawable.book_ribbon),
                    buttonText = "Wikipedia"
                )
                Spacer(modifier = Modifier.width(12.dp))
                BorderedLinkButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    onClick = {
                        viewModel.onEvent(DetailEvent.OnReadArticleClick(uiState.articleUrl))
                    },
                    paninterResource = painterResource(Res.drawable.article),
                    buttonText = "Article"
                )
            }



        }

    }

}