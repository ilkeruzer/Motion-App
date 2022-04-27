package com.ilkeruzer.rapsodotask.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ilkeruzer.rapsodotask.data.local.model.MotionEntity

@Dao
interface MotionDao {

    @Query("SELECT * FROM motion")
    fun getAllMotions(): PagingSource<Int, MotionEntity>

    @Insert
    fun insertMotion(vararg motionEntity: MotionEntity)
}