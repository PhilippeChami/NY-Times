package com.nytimes.android.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nytimes.android.common.LiveDataTestUtil
import com.nytimes.android.common.MainCoroutineRule
import com.nytimes.android.common.ModelUtils
import com.nytimes.android.core.vo.Status
import com.nytimes.android.news.data.repo.MostViewedNewsRepository
import com.nytimes.android.news.domain.MostViewedNewsUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MostViewedUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var repo: MostViewedNewsRepository


    @Before
    fun init() {
    }

    @Test
    fun test_MostViewed_successTest() = mainCoroutineRule.runBlockingTest {

        repo = mock {
            onBlocking { getMostPopularNews() } doReturn ModelUtils.getLiveDataEmptySuccessList()
        }


        val mostViewedUseCase = MostViewedNewsUseCaseImpl(repo)

        val result = mostViewedUseCase.getMostPopularNews()

        assert(LiveDataTestUtil.getValue(result)?.status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result)?.data == ModelUtils.getLiveDataEmptySuccessList().value?.data)

    }


    @Test
    fun test_MostViewed_errorTest() = mainCoroutineRule.runBlockingTest {


        repo = mock {
            onBlocking { getMostPopularNews() } doReturn ModelUtils.getLiveDataEmptyErrorList()
        }

        val mostViewedUseCase = MostViewedNewsUseCaseImpl(repo)

        val result = mostViewedUseCase.getMostPopularNews()

        assert(LiveDataTestUtil.getValue(result)?.status == Status.ERROR)
    }
}
