package com.kdopenapp.quicksched.schedule.presentation.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kdopenapp.quicksched.schedule.common.Days
import com.kdopenapp.quicksched.schedule.domain.subject.model.Subject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSubject(
    create: (Subject) -> Unit
) {
    val dayList = Days.values()
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var subjectDays by remember { mutableStateOf(emptyList<Days>()) }
    Column {
        Text(text = "Create a subject", style = MaterialTheme.typography.headlineMedium)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Name") },
                    value = name,
                    onValueChange = { name = it })
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Description") },
                    value = description,
                    onValueChange = { description = it })
                Text(text = "Days", style = MaterialTheme.typography.bodyMedium)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    dayList.forEach { dayOfButton ->
                        DayChip(day = dayOfButton, remove = {
                            subjectDays = if (subjectDays.contains(dayOfButton)) {
                                subjectDays.filter { it != dayOfButton }
                            } else {
                                subjectDays + dayOfButton
                            }
                        })
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayChip(
    day: Days,
    remove: () -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        modifier = Modifier.padding(5.dp),
        selected = selected,
        onClick = {
            selected = !selected
            remove()
        },
        label = { Text(text = day.name.substring(range = 0..2)) },
        shape = CircleShape
    )
}