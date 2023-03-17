package com.kdopenapp.quicksched.schedule.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun TwoOptionsDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            Button(
                onClick = onPositiveClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = positiveButtonText)
            }
        },
        dismissButton = {
            Button(
                onClick = onNegativeClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = negativeButtonText)
            }
        }
    )
}