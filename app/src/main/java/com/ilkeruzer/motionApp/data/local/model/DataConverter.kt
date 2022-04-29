package com.ilkeruzer.motionApp.data.local.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class DataConverter {

    var gson: Gson = Gson()

    @TypeConverter
    fun stringToMotionCoordinateList(data: String?): List<MotionCoordinates?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<MotionCoordinates?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun motionCoordinateListToString(someObjects: List<MotionCoordinates?>?): String? {
        return gson.toJson(someObjects)
    }
}