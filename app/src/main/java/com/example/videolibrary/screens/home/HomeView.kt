package com.example.videolibrary.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videolibrary.R
import com.example.videolibrary.business.trendingtvseries.TvSeries
import com.example.videolibrary.databinding.LayoutHomeTvseriesTrendingItemBinding
import com.example.videolibrary.databinding.LayoutHomeViewBinding
import com.example.videolibrary.screens.common.imageloader.ImageLoader
import com.example.videolibrary.screens.common.views.BaseView

class HomeView(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    imageLoader: ImageLoader
) : BaseView<HomeView.Listener>(
    layoutInflater,
    parent,
    R.layout.layout_home_view
) {

    interface Listener {

    }

    private var binding: LayoutHomeViewBinding
    private val tvSeriesAdapter: TvSeriesAdapter

    init {
        binding = LayoutHomeViewBinding.inflate(LayoutInflater.from(context), parent, true)
        binding.homeTrendingRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tvSeriesAdapter = TvSeriesAdapter({ clickedItem ->
            for (listener in listeners) {
//                listener.onTrendingTvSeriesClicked(clickedItem)
            }
        }, imageLoader)
        binding.homeTrendingRecycler.adapter = tvSeriesAdapter
    }

    fun bindTvSeries(tvSeries: List<TvSeries>) {
        tvSeriesAdapter.bindData(tvSeries)
    }

    class TvSeriesAdapter(
        private val onItemClickListener: (TvSeries) -> Unit,
        private val imageLoader: ImageLoader
    ) : RecyclerView.Adapter<TvSeriesAdapter.TvSeriesViewHolder>() {

        private var tvSeriesList: List<TvSeries> = ArrayList(0)

        fun bindData(tvSeries: List<TvSeries>) {
            tvSeriesList = ArrayList(tvSeries)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
            val binding = LayoutHomeTvseriesTrendingItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return TvSeriesViewHolder(binding)
        }

        override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
            val tvSeriesListItem = tvSeriesList[position]
            holder.title.text = tvSeriesListItem.name
            imageLoader.loadImage(tvSeriesListItem.posterPath ?: "", holder.image)
            holder.itemView.setOnClickListener {
                onItemClickListener.invoke(tvSeriesListItem)
            }
        }

        override fun getItemCount(): Int {
            val x = 10
            return tvSeriesList.size
        }

        inner class TvSeriesViewHolder(
            binding: LayoutHomeTvseriesTrendingItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            val title: TextView = binding.homeTvSeriesTrendingItemTitle
            val image: ImageView = binding.homeTvSeriesTrendingItemImage
        }
    }
}