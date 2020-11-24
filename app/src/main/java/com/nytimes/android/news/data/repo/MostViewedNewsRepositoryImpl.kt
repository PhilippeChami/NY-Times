package com.nytimes.android.news.data.repo


import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.nytimes.android.core.vo.Resource
import com.nytimes.android.news.data.local.MostViewedNewsLocalDataSource
import com.nytimes.android.news.data.remote.MostViewedNewsRemoteDataSource
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel
import com.nytimes.android.utils.ConnectionUtils
import com.nytimes.android.utils.GlobalFunctions
import kotlinx.coroutines.*
import retrofit2.HttpException


class MostViewedNewsRepositoryImpl constructor(
    private val remoteDataSource: MostViewedNewsRemoteDataSource,
    private val localDataSource: MostViewedNewsLocalDataSource,
    private val context: Context
) : MostViewedNewsRepository {

    private var mutableLiveData = MutableLiveData<Resource<GlobalResponse<List<NewsModel>>>>()
    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)


    override fun getMostPopularNews(): MutableLiveData<Resource<GlobalResponse<List<NewsModel>>>> {
        mutableLiveData = MutableLiveData()
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                mutableLiveData.value = Resource.loading(null)
            }
            val localRequest = localDataSource.getMostPopularNews()
            withContext(Dispatchers.Main) {
                try {
                    mutableLiveData.value = localRequest
                } catch (e: HttpException) {
                    GlobalFunctions.printException(e)

                } catch (e: Throwable) {
                    GlobalFunctions.printException(e)
                }
            }
            if (ConnectionUtils.CheckNetwork(context)) {
                val remoteRequest = remoteDataSource.getMostPopularNews()
                remoteRequest.data?.results = remoteRequest.data?.results?.sortedByDescending { it.updated }!!
                withContext(Dispatchers.Main) {
                    try {
                        mutableLiveData.value = remoteRequest
                    } catch (e: HttpException) {
                        GlobalFunctions.printException(e)

                    } catch (e: Throwable) {
                        GlobalFunctions.printException(e)
                    }
                }

                remoteRequest.data?.results?.let {
                    localDataSource.deleteAllNews() // to avoid data conflict between online and offline
                    localDataSource.saveNews(it)
                }
            }
        }
        return mutableLiveData
    }

}