package com.kdopenapp.quicksched.schedule.presentation.subject

import com.kdopenapp.quicksched.schedule.domain.subject.model.Subject
import com.kdopenapp.quicksched.schedule.domain.subject.model.SubjectLocal

class SubjectState (
    val message : String? = null,
    val data : List<SubjectLocal> = emptyList(),
    val isLoading : Boolean = false
        )