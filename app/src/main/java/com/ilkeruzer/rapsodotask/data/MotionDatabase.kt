package com.ilkeruzer.rapsodotask.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MotionEntity::class], version = 1,exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MotionDatabase: RoomDatabase() {

    companion object {
        fun create(context: Context): MotionDatabase {
            return Room.databaseBuilder(
                context,
                MotionDatabase::class.java,
                "motions.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun motionDao(): MotionDao
}