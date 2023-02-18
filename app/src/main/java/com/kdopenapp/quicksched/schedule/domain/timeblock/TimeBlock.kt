package com.kdopenapp.quicksched.schedule.domain.timeblock

import com.kdopenapp.quicksched.schedule.common.Days


data class TimeBlock(
    val hourStart: Int,
    val minutesStart: Int,
    val endHour: Int,
    val endMinutes: Int,
    val days: List<Days>
)
