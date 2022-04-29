package com.ilkeruzer.motionApp.di

import com.ilkeruzer.motionApp.data.MotionDatabase
import com.ilkeruzer.motionApp.data.local.MotionDao
import com.ilkeruzer.motionApp.data.repository.MotionRepository
import com.ilkeruzer.motionApp.data.repository.MotionRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MotionModule {

    @Binds
    abstract fun provideMotionRepository(
        motionRepositoryImp: MotionRepositoryImp
    ): MotionRepository


    companion object {

        @Provides
        fun provideMotionDao(motionDatabase: MotionDatabase): MotionDao {
            return motionDatabase.motionDao()
        }
    }
}