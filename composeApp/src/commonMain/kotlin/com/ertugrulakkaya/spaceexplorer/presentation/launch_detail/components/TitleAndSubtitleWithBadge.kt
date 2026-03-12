package com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ertugrulakkaya.spaceexplorer.presentation.launch_list.components.LaunchStatusBadge

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
                modifier = Modifier.weight(1f),
                text = missionName
            )
            Spacer(modifier = Modifier.width(8.dp))
            LaunchStatusBadge(
                isLaunchSucessful = isLaunchSuccess
            )
        }
        Text(
            text = rocketName
        )
    }
}