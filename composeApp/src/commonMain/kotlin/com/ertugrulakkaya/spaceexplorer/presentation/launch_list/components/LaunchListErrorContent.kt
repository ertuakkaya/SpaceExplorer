package com.ertugrulakkaya.spaceexplorer.presentation.launch_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components.FilledButtonWithIcon
import org.jetbrains.compose.resources.painterResource
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.replay

@Composable
fun LaunchListErrorContent(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Launch Date Unavailable",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "We are unable to retrieve the launch information for this launch.",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(36.dp))

            FilledButtonWithIcon(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonText = "TRY AGAIN",
                paninterResource = painterResource(Res.drawable.replay),
                onClick = onRetryClick
            )
        }

    }


}