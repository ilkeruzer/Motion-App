package com.ilkeruzer.rapsodotask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ilkeruzer.rapsodotask.data.local.model.MotionEntity

@Dao
interface MotionDao {

    @Query("SELECT * FROM motion")
    fun getAllMotions(): List<MotionEntity>

    @Insert
    fun insertMotion(vararg motionEntity: MotionEntity)
}