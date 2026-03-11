package com.ertugrulakkaya.spaceexplorer.presentation.launch_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import com.ertugrulakkaya.spaceexplorer.presentation.util.toDate
import org.jetbrains.compose.resources.painterResource
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.chevron_right

@Composable
fun LaunchCard(
    modifier: Modifier = Modifier,
    launch: Launch,
    onClick: () -> Unit
){
    Card (
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        border = BorderStroke(color = MaterialTheme.colorScheme.tertiary, width = 1.dp)

    ){
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            LauchCardBadgeIcon(
                badgeUrl = launch.patchImageSmall
            )
            Spacer(modifier = Modifier.width(16.dp))
            LaunchDetails(
                modifier = Modifier.weight(1f),
                launchName = launch.name,
                launchDate = launch.dateUtc.toDate(),
                launchRocket = launch.rocketName ?: "Unknown"
            )
            Spacer(modifier = Modifier.width(16.dp))

            LaunchStatusBadge(
                modifier = Modifier,
                isLaunchSucessful = launch.success ?: false
            )
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(Res.drawable.chevron_right),
                contentDescription = "right icon"
            )

        }
    }

}


@Composable
private fun LauchCardBadgeIcon(
    modifier: Modifier = Modifier,
    badgeUrl : String?

){
    Card(
        modifier = modifier
            .size(64.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        border = BorderStroke(color = MaterialTheme.colorScheme.tertiary, width = 1.dp)
    ){
        AsyncImage(
            model = badgeUrl,
            contentDescription = null,
            modifier = Modifier.padding(8.dp),
            alignment = Alignment.Center
        )
    }
}

@Composable
private fun LaunchDetails(
    modifier: Modifier = Modifier,
    launchName : String,
    launchDate : String,
    launchRocket : String
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            text = launchName
        )
        Text(
            text = launchDate
        )
        Text(
            text = launchRocket
        )
    }
}

@Composable
 fun LaunchStatusBadge(
    modifier: Modifier = Modifier,
    isLaunchSucessful : Boolean

) {

    val badgeColor = if (isLaunchSucessful)
        MaterialTheme.colorScheme.onPrimaryFixed.copy(alpha = 0.15f)
    else
        MaterialTheme.colorScheme.onError.copy(alpha = 0.15f)

    val badgeTextColor = if (isLaunchSucessful)
        MaterialTheme.colorScheme.onPrimaryFixed
    else
        MaterialTheme.colorScheme.onError

    Badge (
        modifier = modifier
            .border(width = 1.dp, color = badgeTextColor, shape = RoundedCornerShape(12.dp)),
        containerColor = badgeColor,
    ){
        Text(
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 10.dp),
            text = if (isLaunchSucessful == true) "Success" else "Failure",
            color = badgeTextColor,
            maxLines = 1
        )
    }
}