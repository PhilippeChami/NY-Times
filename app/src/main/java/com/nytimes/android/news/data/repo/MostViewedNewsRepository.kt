package com.nytimes.android.news.data.repo

import androidx.lifecycle.MutableLiveData
import com.nytimes.android.core.vo.Resource
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel

interface MostViewedNewsRepository {

    fun getMostPopularNews(): MutableLiveData<Resource<GlobalResponse<List<NewsModel>>>>

}