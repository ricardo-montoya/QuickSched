package com.kdopenapp.quicksched.schedule.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kdopenapp.quicksched.schedule.common.Days
import com.kdopenapp.quicksched.schedule.presentation.components.FabBottomRight
import com.kdopenapp.quicksched.schedule.presentation.navigation.Destinations
import com.kdopenapp.quicksched.schedule.presentation.subject.SubjectState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchedulesOfTheDay(
    tabIndex: Int,
    stateValue: SubjectState,
    modifier: Modifier,
    navigate: (String) -> Unit
) {
    val days = Days.values()
    val listToShow = stateValue.data.filter {
        it.timeBlocks.any { timeBlock ->
            timeBlock.days.contains(days[tabIndex])
        }
    }
    LazyColumn(modifier = modifier) {
        items(listToShow) {
            Text(text = it.toString())
            Spacer(modifier = Modifier.background(MaterialTheme.colorScheme.onBackground))
        }
    }
}
