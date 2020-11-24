package com.nytimes.android.core.di.modules


import com.nytimes.android.news.di.MostViewedDetailsModule
import com.nytimes.android.news.di.MostViewedNewsModule
import com.nytimes.android.news.presentation.ui.fragments.MostViewedDetailsFragment
import com.nytimes.android.news.presentation.ui.fragments.MostViewedNewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInjectorsModule {

      @ContributesAndroidInjector(modules = [MostViewedNewsModule::class])
      abstract fun mostViewedNewsFragmentInjector(): MostViewedNewsFragment

      @ContributesAndroidInjector(modules = [MostViewedDetailsModule::class])
      abstract fun mostViewedDetailsFragmentInjector(): MostViewedDetailsFragment

}