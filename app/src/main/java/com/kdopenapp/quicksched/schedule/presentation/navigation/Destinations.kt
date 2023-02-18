package com.kdopenapp.quicksched.schedule.presentation.navigation

sealed class Destinations(
    val route : String
){
    object MainScreen : Destinations("mainScreen")
    object SubjectDetail : Destinations("subjectDetail")
    object CreateSubject: Destinations("addSubject")
}
