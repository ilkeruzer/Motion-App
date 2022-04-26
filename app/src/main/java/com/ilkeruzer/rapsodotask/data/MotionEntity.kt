package com.ilkeruzer.rapsodotask.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "motion")
data class MotionEntity(
    @PrimaryKey val uid: Int,

    @ColumnInfo (name = "coordinates")
    @TypeConverters(MotionCoordinates::class)
    val coordinates: List<MotionCoordinates>? = null
)
