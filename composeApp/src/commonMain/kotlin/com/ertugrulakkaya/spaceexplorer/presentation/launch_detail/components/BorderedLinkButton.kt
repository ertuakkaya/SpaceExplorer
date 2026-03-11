package com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.play_circle

@Composable
fun BorderedLinkButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    paninterResource : Painter = painterResource(Res.drawable.play_circle),
    buttonText : String = "Watch Webcast"
){
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
        ,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(color = MaterialTheme.colorScheme.tertiary, width = 1.dp)
    ){
        Row (
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = paninterResource,
                contentDescription = "Icon button"
            )

            Spacer(modifier = Modifier.width(8.dp),)

            Text(
                text = buttonText
            )
        }
    }
}