package com.nytimes.android.news.data.remote

import com.nytimes.android.core.api.RestService
import com.nytimes.android.core.util.ResponseHandler
import com.nytimes.android.core.vo.Resource
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel


class MostViewedNewsRemoteDataSourceImpl constructor(
    private val restService: RestService,
    private val responseHandler: ResponseHandler
) : MostViewedNewsRemoteDataSource {

    override suspend fun getMostPopularNews(): Resource<GlobalResponse<List<NewsModel>>> {
        return try {
            responseHandler.handleSuccess(restService.getMostPopularNews())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}
