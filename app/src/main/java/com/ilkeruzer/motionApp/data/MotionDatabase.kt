package com.ilkeruzer.motionApp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ilkeruzer.motionApp.data.local.MotionDao
import com.ilkeruzer.motionApp.data.local.model.DataConverter
import com.ilkeruzer.motionApp.data.local.model.MotionEntity

@Database(entities = [MotionEntity::class], version = 2,exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MotionDatabase: RoomDatabase() {
    abstract fun motionDao(): MotionDao
}