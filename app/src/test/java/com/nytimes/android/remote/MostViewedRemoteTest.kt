package com.nytimes.android.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nytimes.android.common.MainCoroutineRule
import com.nytimes.android.common.ModelUtils
import com.nytimes.android.core.api.RestService
import com.nytimes.android.core.util.ResponseHandler
import com.nytimes.android.core.vo.Status
import com.nytimes.android.news.data.remote.MostViewedNewsRemoteDataSourceImpl
import com.nytimes.android.news.models.GlobalResponse
import com.nytimes.android.news.models.NewsModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MostViewedRemoteTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var restService: RestService



    @Before
    fun init() {
    }

    @Test
    fun test_MostViewed_successTest() = mainCoroutineRule.runBlockingTest {

        restService = mock {
            onBlocking { getMostPopularNews() } doReturn ModelUtils.getResponseSuccessList()
        }


        val mostViewedRemote = MostViewedNewsRemoteDataSourceImpl(restService, ResponseHandler())

        val result = mostViewedRemote.getMostPopularNews()


        assert(result?.status == Status.SUCCESS)
        assert(result?.data == ModelUtils.getResponseSuccessList())

    }


    @Test
    fun test_MostViewed_testListNotEmpty() = mainCoroutineRule.runBlockingTest {


        restService = mock {
            onBlocking { getMostPopularNews() } doReturn  ModelUtils.getResponseNotEmptyList()
        }

        val mostViewedRemote = MostViewedNewsRemoteDataSourceImpl(restService, ResponseHandler())

        val result = mostViewedRemote.getMostPopularNews()

        assert(result.status == Status.SUCCESS)
        assert((result.data as GlobalResponse<List<NewsModel>>).results?.size!! > 0)
    }
}
