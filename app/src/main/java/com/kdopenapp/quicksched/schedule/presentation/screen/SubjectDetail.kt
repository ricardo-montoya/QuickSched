package com.kdopenapp.quicksched.schedule.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kdopenapp.quicksched.schedule.domain.subject.model.SubjectLocal
import com.kdopenapp.quicksched.schedule.presentation.components.TwoOptionsDialog
import com.kdopenapp.quicksched.schedule.presentation.components.timeFormattedNumber

@Composable
fun SubjectDetailComposable(
    subject: SubjectLocal,
    delete: () -> Unit
) {
    var deleteState by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = subject.name,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Text(text = subject.description, style = MaterialTheme.typography.bodyMedium)
        Text(
            text = timeFormattedNumber(subject.timeBlocks[0].hourStart)
                    + ':' + timeFormattedNumber(subject.timeBlocks[0].hourEnd)
                    + " to " + timeFormattedNumber(subject.timeBlocks[0].hourEnd) + ':' +
                    timeFormattedNumber(subject.timeBlocks[0].minuteEnd)
        )
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), onClick = { deleteState = true}) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            Spacer(
                modifier = Modifier
                    .width(10.dp)
                    .background(MaterialTheme.colorScheme.background)
            )
            Text(text = "Delete", style = MaterialTheme.typography.labelLarge)
        }
    }
    if (deleteState) {
        TwoOptionsDialog(
            title = "Are you sure you want to delete?",
            message = "This action can't be undone, make sure you want to delete this subject.",
            positiveButtonText = "Delete",
            negativeButtonText = "Cancel",
            onPositiveClick = { //On 'Delete' Click
                              delete()
            },
            onNegativeClick = {deleteState = false} //On 'Cancel' click
        )
    }
}


