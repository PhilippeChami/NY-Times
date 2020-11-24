package com.nytimes.android.news.data.local

import android.content.Context
import com.nytimes.android.core.util.ResponseHandler
import com.nytimes.android.core.vo.Resource
import com.nytimes.android.database.dao.NewsDao
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel
import com.nytimes.android.utils.ConnectionUtils


class MostViewedNewsLocalDataSourceImpl constructor(
    private val newsDao: NewsDao,
    private val responseHandler: ResponseHandler,
    private val context: Context
) : MostViewedNewsLocalDataSource {

    override suspend fun getMostPopularNews(): Resource<GlobalResponse<List<NewsModel>>> {
        return try {
            val news = newsDao.getNews()
            val response: GlobalResponse<List<NewsModel>> = GlobalResponse(news,news.size,"",null)

            if(ConnectionUtils.CheckNetwork(context)){
                responseHandler.handleSuccessWithLoading(response)
            }else{
                responseHandler.handleSuccess(response)
            }
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun saveNews(news: List<NewsModel>) {
        newsDao.insertList(news)
    }

    override suspend fun deleteAllNews() {
        newsDao.deleteAllNews()
    }


}
