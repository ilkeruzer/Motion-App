package com.ilkeruzer.rapsodotask.data.repository

import androidx.paging.PagingSource
import com.ilkeruzer.rapsodotask.data.local.model.MotionEntity

interface MotionRepository {

    suspend fun insertMotionData(motionEntity: MotionEntity) {}

    fun getAllMotion(): PagingSource<Int, MotionEntity>
}