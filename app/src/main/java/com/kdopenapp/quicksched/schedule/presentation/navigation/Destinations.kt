package com.kdopenapp.quicksched.schedule.presentation.navigation

sealed class Destinations(
    val route : String
){
    object MainScreen : Destinations("mainScreen")
    object SubjectDetail : Destinations("subjectDetail/{id}"){
        fun createRouteWithId(id : String) = "subjectDetail/$id"
    }
    object CreateSubject: Destinations("addSubject")
}
