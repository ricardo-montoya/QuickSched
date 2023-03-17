package com.kdopenapp.quicksched.schedule.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kdopenapp.quicksched.schedule.domain.subject.model.SubjectLocal
import com.kdopenapp.quicksched.schedule.common.*

@Composable
fun ScheduleListItem(
    subject: SubjectLocal,
    navigate: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = AppColors[subject.color] ?: MaterialTheme.colorScheme.primary)
            .padding(vertical = 20.dp, horizontal = 10.dp)
            .clickable { navigate() },
    ) {
        Row {
            Text(
                text = timeFormattedNumber(subject.timeBlocks[0].hourStart) +
                        ":" + timeFormattedNumber(subject.timeBlocks[0].minuteStart),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(0.15f),
                color = Color.White
            )
            Text(
                modifier = Modifier.weight(0.85f),
                text = subject.name,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        }
    }
}

fun timeFormattedNumber(number: Int): String {
    return if (number < 10) "0$number" else "$number"
}