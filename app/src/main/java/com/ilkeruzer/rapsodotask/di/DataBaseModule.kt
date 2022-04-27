package com.ilkeruzer.rapsodotask.di

import android.content.Context
import androidx.room.Room
import com.ilkeruzer.rapsodotask.data.local.MotionDao
import com.ilkeruzer.rapsodotask.data.MotionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideMotionDatabase(@ApplicationContext appContext: Context): MotionDatabase {
        return Room.databaseBuilder(
            appContext,
            MotionDatabase::class.java,
            "motions.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}