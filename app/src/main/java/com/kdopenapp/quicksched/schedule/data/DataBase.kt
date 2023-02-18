package com.kdopenapp.quicksched.schedule.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kdopenapp.quicksched.schedule.data.subject.source.SubjectDao
import com.kdopenapp.quicksched.schedule.domain.subject.model.Subject

@Database(version = 1, entities = [Subject::class])
abstract class DataBase : RoomDatabase(){
    abstract fun getSubjectDao() : SubjectDao

    companion object DATA_BASE_NAME{
        const val DATABASE_NAME = "schedule"
    }
}