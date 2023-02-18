package com.kdopenapp.quicksched.schedule.domain.subject.model.repository

import com.kdopenapp.quicksched.schedule.common.Resource
import com.kdopenapp.quicksched.schedule.data.subject.source.SubjectDao
import com.kdopenapp.quicksched.schedule.domain.subject.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository{
        fun getSubjects() : Flow<List<Subject>>

        suspend fun insertSubject(subject: Subject)

        suspend fun deleteSubject(subject: Subject)

}