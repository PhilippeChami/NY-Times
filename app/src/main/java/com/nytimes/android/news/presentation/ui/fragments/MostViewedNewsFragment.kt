package com.nytimes.android.news.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nytimes.android.R
import com.nytimes.android.core.base.BaseFragment
import com.nytimes.android.core.di.ViewModelInjectionField
import com.nytimes.android.core.di.qualifiers.ViewModelInjection
import com.nytimes.android.core.vo.Status
import com.nytimes.android.news.models.NewsModel
import com.nytimes.android.news.presentation.ui.adapters.MostViewedNewsAdapter
import com.nytimes.android.news.presentation.viewmodel.MostViewedNewsViewModel
import com.nytimes.android.utils.GlobalFunctions
import kotlinx.android.synthetic.main.fragment_most_viewed_news.view.*
import javax.inject.Inject


class MostViewedNewsFragment : BaseFragment() {

    @Inject
    @ViewModelInjection
    lateinit var viewModel: ViewModelInjectionField<MostViewedNewsViewModel>

    lateinit private var root: View

    var mostViewedNewsAdapter:MostViewedNewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_most_viewed_news, container, false)

        mActivity = activity

        setupViews()

        if(mostViewedNewsAdapter == null)
            fetchNews()
        else
            root.rvMostViewed.adapter = mostViewedNewsAdapter

        return root
    }

    private fun setupViews(){
        root.rvMostViewed.layoutManager = LinearLayoutManager(mActivity)
        root.srlMostViewed.setOnRefreshListener {
            fetchNews()
        }
    }


    private fun fetchNews(){
        viewModel.get().getMostPopularNews()
        viewModel.get().response.removeObservers(viewLifecycleOwner)
        viewModel.get().response.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { repos ->
                repos?.let {
                    when (repos.status) {
                        Status.LOADING -> {
                            showLoading()
                        }
                        Status.ERROR -> {
                            showError(repos.message)
                            addViews(arrayListOf())
                            hideLoading()
                        }
                        Status.SUCCESS -> {
                            hideLoading()
                            addViews(repos.data?.results)
                        }
                        Status.SUCCESS_WITH_LOADING -> { // load results but keep loading, this is for localdatasource with internet connection
                            addViews(repos.data?.results)
                        }
                    }
                }
            })
    }

    private fun addViews(news:List<NewsModel>?){
        mostViewedNewsAdapter = MostViewedNewsAdapter(news)
        root.rvMostViewed.adapter = mostViewedNewsAdapter
    }

    private fun showError(msg:String?){
        mActivity?.let { it1 -> msg?.let { it2 ->
            GlobalFunctions.showToast(it1,
                it2,Toast.LENGTH_SHORT)
            }
        }
    }

    private fun showLoading() {
        root.srlMostViewed.isRefreshing = true
    }

    private fun hideLoading() {
        root.srlMostViewed.isRefreshing = false
    }


}
