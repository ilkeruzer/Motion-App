package com.ilkeruzer.rapsodotask.data.repository

import com.ilkeruzer.rapsodotask.data.local.model.MotionEntity

interface MotionRepository {

    suspend fun insertMotionData(motionEntity: MotionEntity) {}
}