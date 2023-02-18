package com.kdopenapp.quicksched.schedule.data.subject.source

import androidx.room.*
import com.kdopenapp.quicksched.schedule.domain.subject.model.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Query("SELECT * FROM subject")
    fun getSubjects() : Flow<List<Subject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject: Subject)

    @Delete
    suspend fun delete(subject: Subject)

}