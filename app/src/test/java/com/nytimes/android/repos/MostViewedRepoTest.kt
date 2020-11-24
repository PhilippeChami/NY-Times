package com.nytimes.android.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nytimes.android.common.LiveDataTestUtil
import com.nytimes.android.common.MainCoroutineRule
import com.nytimes.android.common.ModelUtils
import com.nytimes.android.core.vo.Status
import com.nytimes.android.news.data.remote.MostViewedNewsRemoteDataSource
import com.nytimes.android.news.data.repo.MostViewedNewsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MostViewedRepoTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var remoteDataSource: MostViewedNewsRemoteDataSource



    @Before
    fun init() {
    }

    @Test
    fun test_MostViewed_successTest() = mainCoroutineRule.runBlockingTest {

        remoteDataSource = mock {
            onBlocking { getMostPopularNews() } doReturn ModelUtils.getEmptySuccessList()
        }


        val mostViewedRepo = MostViewedNewsRepositoryImpl(remoteDataSource)

        val result = mostViewedRepo.getMostPopularNews()


        assert(LiveDataTestUtil.getValue(result)?.status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result)?.status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result)?.data == ModelUtils.getEmptySuccessList().data)

    }


    @Test
    fun test_MostViewed_errorTest() = mainCoroutineRule.runBlockingTest {


        remoteDataSource = mock {
            onBlocking { getMostPopularNews() } doReturn ModelUtils.getEmptyErrorList()
        }

        val mostViewedRepo = MostViewedNewsRepositoryImpl(remoteDataSource)

        val result = mostViewedRepo.getMostPopularNews()

        assert(LiveDataTestUtil.getValue(result)?.status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result)?.status == Status.ERROR)
    }
}
