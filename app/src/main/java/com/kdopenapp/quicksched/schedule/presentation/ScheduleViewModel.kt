package com.kdopenapp.quicksched.schedule.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdopenapp.quicksched.schedule.common.Days
import com.kdopenapp.quicksched.schedule.domain.subject.model.SubjectLocal
import com.kdopenapp.quicksched.schedule.domain.subject.model.repository.SubjectRepository
import com.kdopenapp.quicksched.schedule.domain.timeblock.TimeBlock
import com.kdopenapp.quicksched.schedule.presentation.subject.SubjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository
) : ViewModel() {

    init {
        //dummyInsert()
        getSubjects()
    }

    /*
    fun dummyInsert() {
        runBlocking {
            val timeBlock = TimeBlock(days = listOf(Days.MONDAY), hourEnd = 19, minuteEnd = 20, hourStart = 20, minuteStart = 10)
            val newSub = SubjectLocal(id = null, name ="Empty", description = "Nothing", color = Random.nextInt(1,7), timeBlocks = listOf(timeBlock))
            subjectRepository.insertSubject(newSub.toSubjectEntity())
        }
        getSubjects()
    }
    */
    fun insertNewSubject(subject: SubjectLocal) {
        runBlocking {
            subjectRepository.insertSubject(subject = subject.toSubjectEntity())
        }
        getSubjects()
    }

    private val _state = mutableStateOf(SubjectState())
    val state = _state

    fun getSubjects() {
        subjectRepository.getSubjects().onEach { subjectList ->
            _state.value = SubjectState(data = subjectList.map { subject ->
                subject.toSubjectLocal()
            })
        }.launchIn(viewModelScope)
    }

    fun deleteSubject(subject: SubjectLocal) {
        runBlocking {
            subjectRepository.deleteSubject(subject = subject.toSubjectEntity())
        }
    }

}
