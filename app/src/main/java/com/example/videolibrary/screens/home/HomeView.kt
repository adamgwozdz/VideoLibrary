package com.example.videolibrary.screens.home

import android.util.Log
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
    private val tvSeriesAdapter1: TvSeriesAdapter1

    init {
        binding = LayoutHomeViewBinding.inflate(LayoutInflater.from(context), parent, true)

        binding.homeTrendingRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tvSeriesAdapter = TvSeriesAdapter({ clickedItem ->
            for (listener in listeners) {
//                listener.onTrendingTvSeriesClicked(clickedItem)
            }
        }, imageLoader)
        binding.homeTrendingRecycler.adapter = tvSeriesAdapter

        binding.homeTrendingRecycler1.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tvSeriesAdapter1 = TvSeriesAdapter1({ clickedItem ->
            for (listener in listeners) {
//                listener.onTrendingTvSeriesClicked(clickedItem)
            }
        }, imageLoader)
        binding.homeTrendingRecycler1.adapter = tvSeriesAdapter1
    }

    fun bindTvSeries(tvSeries: List<TvSeries>) {
        tvSeriesAdapter.bindData(tvSeries)
    }

    fun bindTvSeries1(tvSeries: List<TvSeries>) {
        tvSeriesAdapter1.bindData(tvSeries)
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
            return tvSeriesList.size
        }

        inner class TvSeriesViewHolder(
            binding: LayoutHomeTvseriesTrendingItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            val title: TextView = binding.homeTvSeriesTrendingItemTitle
            val image: ImageView = binding.homeTvSeriesTrendingItemImage
        }
    }

    class TvSeriesAdapter1(
        private val onItemClickListener: (TvSeries) -> Unit,
        private val imageLoader: ImageLoader
    ) : RecyclerView.Adapter<TvSeriesAdapter1.TvSeriesViewHolder>() {

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