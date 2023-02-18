package com.kdopenapp.quicksched.schedule.data

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.kdopenapp.quicksched.schedule.data.subject.source.SubjectRepositoryImp
import com.kdopenapp.quicksched.schedule.domain.subject.model.repository.SubjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesSubjectRepository(db : DataBase) : SubjectRepository{
        return SubjectRepositoryImp(db.getSubjectDao())
    }

    @Provides
    @Singleton
    fun providesDatabase(app : Application) : DataBase{
        return Room.databaseBuilder(
            app,
            DataBase::class.java,
            DataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesGson() : Gson{
        return Gson()
    }
}