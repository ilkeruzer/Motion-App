package com.ilkeruzer.rapsodotask.di

import com.ilkeruzer.rapsodotask.data.MotionDatabase
import com.ilkeruzer.rapsodotask.data.local.MotionDao
import com.ilkeruzer.rapsodotask.data.repository.MotionRepository
import com.ilkeruzer.rapsodotask.data.repository.MotionRepositoryImp
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