package com.kdopenapp.quicksched

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kdopenapp.quicksched.schedule.data.DataBase
import com.kdopenapp.quicksched.schedule.data.subject.source.SubjectDao
import com.kdopenapp.quicksched.schedule.domain.subject.model.Subject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

class RoomTests {
    @RunWith(AndroidJUnit4::class)
    class SimpleEntityReadWriteTest {
        private lateinit var subjectDao: SubjectDao
        private lateinit var db: DataBase

        @Before
        fun createDb() {
            val context = ApplicationProvider.getApplicationContext<Context>()
            db = Room.inMemoryDatabaseBuilder(
                context, DataBase::class.java
            ).build()
            subjectDao = db.getSubjectDao()
        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            db.close()
        }

        @Test
        @Throws(Exception::class)
        fun writeSubjectAndReadInList() {
            val subject: Subject = Subject(
                4982,
                "Free time",
                "Some Subject",
                1
            )
            runBlocking {
                subjectDao.insert(subject)
            val byName  = subjectDao.getSubjects().map { it }
            assertThat(byName.first(), equalTo(subject))

            }
        }
    }
}