package com.kdopenapp.quicksched.schedule.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kdopenapp.quicksched.schedule.presentation.ScheduleViewModel
import com.kdopenapp.quicksched.schedule.presentation.navigation.Destinations

@Composable
fun MainScreenComposable(
    viewModel: ScheduleViewModel,
    navController: NavHostController
) {
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
        SubjectsSpecificDay(
            modifier = Modifier.weight(0.9F),
            tabIndex = tabIndex,
            stateValue = stateValue,
            navigate = { id ->
                navController.navigate(Destinations.SubjectDetail.createRouteWithId(id))
            }
        )
    }
}