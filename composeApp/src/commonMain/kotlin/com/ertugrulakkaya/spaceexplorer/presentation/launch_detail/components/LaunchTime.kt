package com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.calendar_today

@Composable
fun LaunchDate(
    modifier: Modifier = Modifier,
    launchDateTime : String
){
    Card (
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        border = BorderStroke(color = MaterialTheme.colorScheme.tertiary, width = 1.dp)

    ){
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconWithBackGround()

            Spacer(modifier = Modifier.width(16.dp))

            DateTime(dateTime = launchDateTime)


        }
    }

}

@Composable
private fun IconWithBackGround(
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.1f)),
        contentAlignment = Alignment.Center

    ){
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(Res.drawable.calendar_today),
            contentDescription = "calender",
            tint = MaterialTheme.colorScheme.secondaryContainer
        )
    }

}

@Composable
private fun DateTime(
    modifier: Modifier = Modifier,
    dateTime : String
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Launch Date",
        )
        Text(
            text = dateTime
        )
    }
}



