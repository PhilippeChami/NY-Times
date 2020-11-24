package com.nytimes.android.news.data.remote

import com.nytimes.android.core.vo.Resource
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel


interface MostViewedNewsRemoteDataSource {

    suspend fun getMostPopularNews(): Resource<GlobalResponse<List<NewsModel>>>

}