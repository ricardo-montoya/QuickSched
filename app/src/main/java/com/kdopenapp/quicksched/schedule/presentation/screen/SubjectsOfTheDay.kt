package com.kdopenapp.quicksched.schedule.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kdopenapp.quicksched.schedule.common.Days
import com.kdopenapp.quicksched.schedule.presentation.components.ScheduleListItem
import com.kdopenapp.quicksched.schedule.presentation.subject.SubjectState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectsSpecificDay(
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
    }.sortedBy { it.timeBlocks[0].hourStart }
    LazyColumn(modifier = modifier) {
        items(listToShow) {
            ScheduleListItem(subject = it){
                navigate("${it.id}")
            }
        }
    }
}
