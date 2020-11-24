package com.nytimes.android.core.di.modules

import android.content.Context
import androidx.room.Room
import com.nytimes.android.database.AppDatabase
import com.nytimes.android.database.dao.NewsDao
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {

    companion object {
        val mDBName = "nyTimes.db"
    }

    private var INSTANCE: AppDatabase? = null


    fun getAppDatabase(mContext: Context, dbName: String): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class.java) {
                if (INSTANCE == null) {
                    val builder = Room.databaseBuilder(
                        mContext,
                        AppDatabase::class.java,
                        dbName
                    )

                    builder.fallbackToDestructiveMigration()
                    builder.enableMultiInstanceInvalidation()

                    INSTANCE = builder.build()
                }
            }
        }
        return INSTANCE!!
    }

    @Provides
    fun provideDatabase(mContext: Context): AppDatabase {
        return getAppDatabase(mContext, mDBName)
    }

    @Provides
    fun provideNewsDao(db: AppDatabase): NewsDao {
        return db.newsDao()
    }

}