package com.ertugrulakkaya.spaceexplorer.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import coil3.compose.AsyncImage
import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import org.jetbrains.compose.resources.painterResource
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.chevron_right
import spaceexplorer.composeapp.generated.resources.compose_multiplatform

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
                launchName = launch.name,
                launchDate = launch.dateUtc,
                launchRocket = launch.rocketName ?: "Unknown"
            )
            Spacer(modifier = Modifier.weight(1f))
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
private fun LaunchStatusBadge(
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(Res.drawable.chevron_right),
        contentDescription = "right icon"
    )
}