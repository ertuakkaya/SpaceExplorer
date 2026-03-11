package com.ertugrulakkaya.spaceexplorer.presentation.launch_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ertugrulakkaya.spaceexplorer.domain.util.openUrl
import com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components.BorderedLinkButton
import com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components.DetailPhoto
import com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components.LaunchDate
import com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components.LaunchDescription
import com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components.TitleAndSubtitleWithBadge
import com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components.FilledButtonWithIcon
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.article
import spaceexplorer.composeapp.generated.resources.book_ribbon
import spaceexplorer.composeapp.generated.resources.chevron_right

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = koinViewModel(),
    onNavigateToDetail: () -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
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
                        text = uiState.missionName,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize

                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(DetailEvent.OnBackClick)
                        }
                    ) {
                        Icon(
                            modifier = Modifier.rotate(180f),
                            painter = painterResource(Res.drawable.chevron_right),
                            contentDescription = "Back",
                            tint = Color.White
                        )


                    }
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

            FilledButtonWithIcon(

                onClick = { viewModel.onEvent(DetailEvent.OnWatchVideoClick(uiState.webCastUrl)) },
                //buttonText = uiState.webCastUrl
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
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