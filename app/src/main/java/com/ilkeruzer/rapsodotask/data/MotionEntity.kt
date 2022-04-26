package com.ilkeruzer.rapsodotask.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "motion")
data class MotionEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,

    @ColumnInfo (name = "coordinates")
    @TypeConverters(MotionCoordinates::class)
    val coordinates: List<MotionCoordinates>? = null
)
