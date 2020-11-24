package com.nytimes.android.news.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import com.nytimes.android.core.base.BaseViewModel
import com.nytimes.android.core.vo.Resource
import com.nytimes.android.news.domain.MostViewedNewsUseCase
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel
import javax.inject.Inject


class MostViewedNewsViewModel @Inject constructor(
    private val mMostViewedNewsUseCase: MostViewedNewsUseCase
) : BaseViewModel() {

    var response: MutableLiveData<Resource<GlobalResponse<List<NewsModel>>>> = MutableLiveData()

    fun getMostPopularNews() {
        response = mMostViewedNewsUseCase.getMostPopularNews()
    }

}