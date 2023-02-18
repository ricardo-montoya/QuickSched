package com.kdopenapp.quicksched.schedule.domain.subject.use_case

import android.util.Log
import com.kdopenapp.quicksched.schedule.common.Resource
import com.kdopenapp.quicksched.schedule.domain.subject.model.Subject
import com.kdopenapp.quicksched.schedule.domain.subject.model.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class GetSubjects @Inject constructor(
    private val repository: SubjectRepository
) {

}