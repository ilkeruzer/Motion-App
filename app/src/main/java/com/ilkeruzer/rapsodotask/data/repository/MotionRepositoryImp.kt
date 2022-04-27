package com.ilkeruzer.rapsodotask.data.repository

import com.ilkeruzer.rapsodotask.data.local.MotionDao
import com.ilkeruzer.rapsodotask.data.local.model.MotionEntity
import javax.inject.Inject

class MotionRepositoryImp @Inject constructor(
    private val motionDao: MotionDao
): MotionRepository  {

    override suspend fun insertMotionData(motionEntity: MotionEntity) {
        motionDao.insertMotion(motionEntity)
    }
}