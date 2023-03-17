package com.kdopenapp.quicksched.schedule.domain.timeblock

import com.kdopenapp.quicksched.schedule.common.Days


data class TimeBlock(
    val hourStart: Int,
    val minuteStart: Int,
    val hourEnd: Int,
    val minuteEnd: Int,
    val days: List<Days>
)
