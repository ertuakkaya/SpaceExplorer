package com.ertugrulakkaya.spaceexplorer.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ertugrulakkaya.spaceexplorer.presentation.home.components.LaunchStatusBadge

@Composable
fun TitleAndSubtitleWithBadge(
    modifier: Modifier = Modifier,
    missionName : String,
    rocketName : String,
    isLaunchSuccess : Boolean
) {

    Column (
        modifier = modifier
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = missionName
            )

            LaunchStatusBadge(
                isLaunchSucessful = isLaunchSuccess
            )
        }
        Text(
            text = rocketName
        )
    }
}