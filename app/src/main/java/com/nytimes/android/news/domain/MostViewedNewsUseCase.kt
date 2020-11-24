package com.nytimes.android.news.domain

import androidx.lifecycle.MutableLiveData
import com.nytimes.android.core.vo.Resource
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel


interface MostViewedNewsUseCase {

    fun getMostPopularNews(): MutableLiveData<Resource<GlobalResponse<List<NewsModel>>>>

}