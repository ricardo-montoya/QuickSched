package com.kdopenapp.quicksched.schedule.data.subject.source

import android.util.Log
import com.kdopenapp.quicksched.schedule.common.Resource
import com.kdopenapp.quicksched.schedule.domain.subject.model.Subject
import com.kdopenapp.quicksched.schedule.domain.subject.model.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class SubjectRepositoryImp (
    private val dao : SubjectDao
        ): SubjectRepository {

    override fun getSubjects() : Flow<List<Subject>>{
        return dao.getSubjects()
    }

    override suspend fun insertSubject(subject: Subject) {
        dao.insert(subject)
    }

    override suspend fun deleteSubject(subject: Subject) {
        dao.delete(subject)
    }


}