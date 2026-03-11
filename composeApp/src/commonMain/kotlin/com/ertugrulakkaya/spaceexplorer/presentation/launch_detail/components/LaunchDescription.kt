package com.ertugrulakkaya.spaceexplorer.presentation.launch_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
 fun LaunchDescription(
    modifier: Modifier = Modifier,
    description : String
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Launch Description",
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description
        )
    }
}