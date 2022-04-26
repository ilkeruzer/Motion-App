package com.ilkeruzer.rapsodotask.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MotionDao {

    @Query("SELECT * FROM motion")
    fun getAllMotions(): List<MotionEntity>

    @Insert
    fun insertMotion(vararg motionEntity: MotionEntity)
}