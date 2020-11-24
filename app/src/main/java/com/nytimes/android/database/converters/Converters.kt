package com.nytimes.android.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.nytimes.android.news.models.Media
import java.lang.reflect.Type
import java.util.*


class Converters {


    @TypeConverter
    fun stringToMediaList(data: String?): List<Media?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Media?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun mediaListToString(someObjects: List<Media?>?): String? {
        return Gson().toJson(someObjects)
    }

}