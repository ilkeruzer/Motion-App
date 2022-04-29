package com.ilkeruzer.motionApp.di

import android.content.Context
import androidx.room.Room
import com.ilkeruzer.motionApp.data.MotionDatabase
import com.ilkeruzer.motionApp.utils.Constants
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
            Constants.MOTION_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}