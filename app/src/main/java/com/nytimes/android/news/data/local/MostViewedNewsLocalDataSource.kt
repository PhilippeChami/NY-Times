package com.nytimes.android.news.data.local

import com.nytimes.android.core.vo.Resource
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel


interface MostViewedNewsLocalDataSource {

    suspend fun getMostPopularNews(): Resource<GlobalResponse<List<NewsModel>>>

    suspend fun saveNews(news:List<NewsModel>)

    suspend fun deleteAllNews()

}