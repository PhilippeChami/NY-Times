package com.nytimes.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nytimes.android.database.converters.Converters
import com.nytimes.android.database.dao.NewsDao
import com.nytimes.android.news.models.NewsModel


@Database(
    entities = [
        NewsModel::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}