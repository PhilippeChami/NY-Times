package com.nytimes.android.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nytimes.android.common.LiveDataTestUtil
import com.nytimes.android.common.MainCoroutineRule
import com.nytimes.android.common.ModelUtils
import com.nytimes.android.core.vo.Status
import com.nytimes.android.news.domain.MostViewedNewsUseCase
import com.nytimes.android.news.presentation.viewmodel.MostViewedNewsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MostViewedViewModeTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var useCase: MostViewedNewsUseCase


    @Before
    fun init() {
    }

    @Test
    fun test_MostViewed_successTest() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getMostPopularNews() } doReturn ModelUtils.getLiveDataEmptySuccessList()
        }


        val mostViewedViewModel = MostViewedNewsViewModel(useCase)

        val result = mostViewedViewModel.getMostPopularNews()

//        assert(LiveDataTestUtil.getValue(result)?.status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(mostViewedViewModel.response)?.status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(mostViewedViewModel.response)?.data == ModelUtils.getLiveDataEmptySuccessList().value?.data)

    }


    @Test
    fun test_MostViewed_errorTest() = mainCoroutineRule.runBlockingTest {


        useCase = mock {
            onBlocking { getMostPopularNews() } doReturn ModelUtils.getLiveDataEmptyErrorList()
        }

        val mostViewedViewModel = MostViewedNewsViewModel(useCase)

        val result = mostViewedViewModel.getMostPopularNews()

//        assert(LiveDataTestUtil.getValue(result)?.status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(mostViewedViewModel.response)?.status == Status.ERROR)
    }
}
