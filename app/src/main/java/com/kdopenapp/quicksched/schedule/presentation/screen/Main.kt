package com.kdopenapp.quicksched.schedule.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.kdopenapp.quicksched.schedule.presentation.ScheduleViewModel
import com.kdopenapp.quicksched.schedule.presentation.navigation.Destinations

@Composable
fun MainScreenComposable(viewModel: ScheduleViewModel, navController: NavHostController) {
    val stateValue = viewModel.state.value
    var tabIndex by remember { mutableStateOf(0) }
    Column() {
        ScheduleTabs(
            selectedTab = { selectedTab ->
                tabIndex = selectedTab
            },
            navigate = { route ->
                navController.navigate(route)
            }
        )
        SchedulesOfTheDay(
            modifier = Modifier.weight(0.9F),
            tabIndex = tabIndex,
            stateValue = stateValue,
            navigate = { path ->
                navController.navigate(path)
            }
        )
    }
}