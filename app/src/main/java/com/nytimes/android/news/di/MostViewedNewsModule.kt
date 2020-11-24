package com.nytimes.android.news.di


import android.content.Context
import com.nytimes.android.core.api.RestService
import com.nytimes.android.core.di.InjectionViewModelProvider
import com.nytimes.android.core.di.qualifiers.ViewModelInjection
import com.nytimes.android.core.util.ResponseHandler
import com.nytimes.android.database.dao.NewsDao
import com.nytimes.android.news.data.local.MostViewedNewsLocalDataSource
import com.nytimes.android.news.data.local.MostViewedNewsLocalDataSourceImpl
import com.nytimes.android.news.data.remote.MostViewedNewsRemoteDataSource
import com.nytimes.android.news.data.remote.MostViewedNewsRemoteDataSourceImpl
import com.nytimes.android.news.data.repo.MostViewedNewsRepository
import com.nytimes.android.news.data.repo.MostViewedNewsRepositoryImpl
import com.nytimes.android.news.domain.MostViewedNewsUseCase
import com.nytimes.android.news.domain.MostViewedNewsUseCaseImpl
import com.nytimes.android.news.presentation.ui.fragments.MostViewedNewsFragment
import com.nytimes.android.news.presentation.viewmodel.MostViewedNewsViewModel
import dagger.Module
import dagger.Provides


@Module
class MostViewedNewsModule {

    @Provides
    @ViewModelInjection
    fun provideMostViewedNewsViewModel(
        fragment: MostViewedNewsFragment,
        viewModelProvider: InjectionViewModelProvider<MostViewedNewsViewModel>
    ) = viewModelProvider.get(fragment, MostViewedNewsViewModel::class)

    @Provides
    fun providesMostViewedNewsUseCase(mMostViewedNewsRepository: MostViewedNewsRepository): MostViewedNewsUseCase =
        MostViewedNewsUseCaseImpl(mMostViewedNewsRepository)


    @Provides
    fun providesMostViewedNewsRepository(mMostViewedNewsRemoteDataSource: MostViewedNewsRemoteDataSource,
                                         mMostViewedNewsLocalDataSource: MostViewedNewsLocalDataSource,
                                         context: Context): MostViewedNewsRepository =
        MostViewedNewsRepositoryImpl(mMostViewedNewsRemoteDataSource, mMostViewedNewsLocalDataSource,context)


    @Provides
    fun providesMostViewedNewsRemoteDataSource(
        restService: RestService,
        responseHandler: ResponseHandler
    ): MostViewedNewsRemoteDataSource =
        MostViewedNewsRemoteDataSourceImpl(restService, responseHandler)

    @Provides
    fun providesMostViewedNewsLocalDataSource(
        newsDao: NewsDao,
        responseHandler: ResponseHandler,
        context: Context
    ): MostViewedNewsLocalDataSource =
        MostViewedNewsLocalDataSourceImpl(newsDao, responseHandler, context)


}