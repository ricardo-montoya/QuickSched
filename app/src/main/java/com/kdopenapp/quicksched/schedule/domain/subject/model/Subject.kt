package com.kdopenapp.quicksched.schedule.domain.subject.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.kdopenapp.quicksched.schedule.domain.timeblock.TimeBlock
import com.kdopenapp.quicksched.ui.theme.*
import dagger.assisted.AssistedInject
import dagger.hilt.android.migration.CustomInjection
import javax.inject.Inject

@Entity
data class Subject constructor(
    val name : String,
    val description : String,
    val color : Int,
    val timeBlocks : String,
    @PrimaryKey val id: Int? = null
) {
    @Ignore val gson = Gson()
    fun toSubjectLocal() : SubjectLocal{
        return SubjectLocal(
            name = this.name,
            description =  this.description,
            color = this.color,
            id =  this.id?:0,
            timeBlocks = gson.fromJson(this.timeBlocks, Array<TimeBlock>::class.java).toList()
        )
    }
}
