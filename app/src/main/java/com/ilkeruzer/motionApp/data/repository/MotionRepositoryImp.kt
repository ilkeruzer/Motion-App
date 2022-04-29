package com.ilkeruzer.motionApp.data.repository

import androidx.paging.PagingSource
import com.ilkeruzer.motionApp.data.local.MotionDao
import com.ilkeruzer.motionApp.data.local.model.MotionEntity
import javax.inject.Inject

class MotionRepositoryImp @Inject constructor(
    private val motionDao: MotionDao
): MotionRepository  {

    override suspend fun insertMotionData(motionEntity: MotionEntity) {
        motionDao.insertMotion(motionEntity)
    }

    override fun getAllMotion(): PagingSource<Int, MotionEntity> =
        motionDao.getAllMotions()
}