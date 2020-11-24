package com.nytimes.android.news.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nytimes.android.core.base.BaseFragment
import com.nytimes.android.core.di.ViewModelInjectionField
import com.nytimes.android.core.di.qualifiers.ViewModelInjection
import com.nytimes.android.databinding.FragmentMostViewedDetailsBinding
import com.nytimes.android.news.models.NewsModel
import com.nytimes.android.news.presentation.viewmodel.MostViewedNewsViewModel
import javax.inject.Inject


class MostViewedDetailsFragment : BaseFragment() {

    @Inject
    @ViewModelInjection
    lateinit var viewModel: ViewModelInjectionField<MostViewedNewsViewModel>

    var newsModel:NewsModel? = null

    var binding: FragmentMostViewedDetailsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mActivity = activity

        println("fragment>>>>>>>>>>>>>>>>>>>>>>>>>")
        setupBindings(inflater)

        return binding?.root
    }

    fun setupBindings(inflater:LayoutInflater){
        newsModel = arguments?.let { MostViewedDetailsFragmentArgs.fromBundle(it).newModel }

        binding = FragmentMostViewedDetailsBinding.inflate(inflater)

        binding?.newsModel = newsModel

        binding?.executePendingBindings()
    }


}
