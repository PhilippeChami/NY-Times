package com.nytimes.android.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.nytimes.android.news.models.NewsModel

@Dao
interface NewsDao : BaseDao<NewsModel> {

    @Query("SELECT * FROM newsModel ORDER BY updated DESC LIMIT 20")
    fun getNews(): List<NewsModel>

    @Query("DELETE FROM newsModel")
    fun deleteAllNews()
}