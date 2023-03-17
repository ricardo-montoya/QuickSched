package com.kdopenapp.quicksched.schedule.presentation.navigation

import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.kdopenapp.quicksched.schedule.presentation.ScheduleViewModel
import com.kdopenapp.quicksched.schedule.presentation.components.TwoOptionsDialog
import com.kdopenapp.quicksched.schedule.presentation.screen.CreateSubject
import com.kdopenapp.quicksched.schedule.presentation.screen.MainScreenComposable
import com.kdopenapp.quicksched.schedule.presentation.screen.SubjectDetailComposable

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    val viewModel: ScheduleViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Destinations.MainScreen.route) {
        composable(Destinations.MainScreen.route) {
            MainScreenComposable(viewModel = viewModel, navController = navController)
        }
        composable(Destinations.SubjectDetail.route) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")
            val subject = viewModel.state.value.data.find { "${it.id}" == id }
            if (subject != null) {
                SubjectDetailComposable(subject) {
                    viewModel.deleteSubject(subject = subject)
                    navController.navigateUp()
                }

            }
        }
        composable(Destinations.CreateSubject.route) {
            CreateSubject() {
                viewModel.insertNewSubject(it)
                navController.navigateUp()
            }
        }
    }
}