package com.nytimes.android.news.domain

import androidx.lifecycle.MutableLiveData
import com.nytimes.android.core.vo.Resource
import com.nytimes.android.news.data.repo.MostViewedNewsRepository
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel

class MostViewedNewsUseCaseImpl constructor(private val mMostViewedNewsRepository: MostViewedNewsRepository) :
    MostViewedNewsUseCase {


    override fun getMostPopularNews(): MutableLiveData<Resource<GlobalResponse<List<NewsModel>>>> = mMostViewedNewsRepository.getMostPopularNews()

}
