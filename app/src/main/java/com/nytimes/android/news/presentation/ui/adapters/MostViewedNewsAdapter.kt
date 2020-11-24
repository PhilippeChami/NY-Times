package com.nytimes.android.news.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.android.databinding.ItemNewsBinding
import com.nytimes.android.news.models.NewsModel
import com.nytimes.android.news.presentation.ui.fragments.MostViewedNewsFragmentDirections


class MostViewedNewsAdapter(
    var news: List<NewsModel>?
) : RecyclerView.Adapter<MostViewedNewsAdapter.MostViewedNewsHolder>() {


    override fun getItemCount(): Int {
        news?.size?.let { return it }
        return 0
    }

    class MostViewedNewsHolder(val binding: ItemNewsBinding?) :
        RecyclerView.ViewHolder(binding?.root!!) {

        fun bind(newsModel: NewsModel) {
            binding?.newsModel = newsModel
            binding?.executePendingBindings()
            binding?.root?.setOnClickListener {v ->
                v.findNavController().navigate(
                    MostViewedNewsFragmentDirections.actionMostViewedNewsFragmentToMostViewedDetailsFragment(newsModel)
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostViewedNewsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemNewsBinding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        return MostViewedNewsHolder(itemBinding)
    }


    override fun onBindViewHolder(holder: MostViewedNewsHolder, position: Int) {
        val newsModel: NewsModel? = news?.get(position)
        newsModel?.let { holder.bind(it) }
    }


}