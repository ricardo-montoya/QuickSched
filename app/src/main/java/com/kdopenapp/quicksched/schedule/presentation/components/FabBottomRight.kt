@file:OptIn(ExperimentalMaterial3Api::class)

package com.kdopenapp.quicksched.schedule.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange

@Composable
fun FabBottomRight(
    fabPosition: FabPosition,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        floatingActionButtonPosition = fabPosition,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClick,
                modifier = modifier,
                content = content
            )
        }
    ){
    }
}