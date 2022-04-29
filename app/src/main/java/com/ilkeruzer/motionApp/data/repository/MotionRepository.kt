package com.ilkeruzer.motionApp.data.repository

import androidx.paging.PagingSource
import com.ilkeruzer.motionApp.data.local.model.MotionEntity

interface MotionRepository {

    suspend fun insertMotionData(motionEntity: MotionEntity) {}

    fun getAllMotion(): PagingSource<Int, MotionEntity>
}