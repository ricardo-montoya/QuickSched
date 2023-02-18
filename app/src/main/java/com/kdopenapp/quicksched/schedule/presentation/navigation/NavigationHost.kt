package com.kdopenapp.quicksched.schedule.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kdopenapp.quicksched.schedule.presentation.ScheduleViewModel
import com.kdopenapp.quicksched.schedule.presentation.screen.CreateSubject
import com.kdopenapp.quicksched.schedule.presentation.screen.MainScreenComposable
import com.kdopenapp.quicksched.schedule.presentation.screen.SubjectDetailComposable

@Composable
fun NavigationHost(){
    val navController = rememberNavController()
    val viewModel : ScheduleViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Destinations.MainScreen.route){
        composable(Destinations.MainScreen.route){
            MainScreenComposable(viewModel = viewModel, navController = navController)
        }
        composable(Destinations.SubjectDetail.route){
            SubjectDetailComposable()
        }
        composable(Destinations.CreateSubject.route){
            CreateSubject(create = {})
        }
    }
}