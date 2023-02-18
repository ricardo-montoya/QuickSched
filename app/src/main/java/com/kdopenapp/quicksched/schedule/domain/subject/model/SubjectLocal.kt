package com.kdopenapp.quicksched.schedule.domain.subject.model

import androidx.room.Ignore
import com.google.gson.Gson
import com.kdopenapp.quicksched.schedule.domain.timeblock.TimeBlock
import javax.inject.Inject

class SubjectLocal(
    var name: String = "",
    val description: String = "",
    val color: Int,
    val timeBlocks: List<TimeBlock> = emptyList(),
    val id: Int? = 0
) {
    @Ignore
    val gson = Gson()
    fun toSubjectEntity(): Subject {
        return Subject(
            id = this.id,
            name = this.name,
            description = this.description,
            color = this.color,
            timeBlocks = gson.toJson(this.timeBlocks)
        )
    }

    override fun toString(): String {
        return "SubjectLocal(name='$name', description='$description', color=$color, timeBlocks=$timeBlocks, id=$id)"
    }

}