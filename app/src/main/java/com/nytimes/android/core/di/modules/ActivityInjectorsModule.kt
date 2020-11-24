package com.nytimes.android.core.di.modules

import com.nytimes.android.news.presentation.ui.activities.MostViewedNewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector
    abstract fun mostViewedNewsActivityInjector(): MostViewedNewsActivity

}