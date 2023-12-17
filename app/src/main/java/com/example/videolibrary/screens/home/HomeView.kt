package com.example.videolibrary.screens.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videolibrary.R
import com.example.videolibrary.business.trendingtvseries.TvSeries
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
        fun onSwitchStateChanged(switchState: TrendingSwitchState)
    }

    private val homeTrendingRecycler: RecyclerView = findViewById(R.id.homeTrendingRecycler)
    private val trendingSwitchLabelLeft: TextView = findViewById(R.id.trendingSwitchLabelLeft)
    private val trendingSwitchLabelRight: TextView = findViewById(R.id.trendingSwitchLabelRight)

    private val tvSeriesAdapter: TvSeriesAdapter
    private var switchState: TrendingSwitchState

    init {
        switchState = TrendingSwitchState.DAY_SELECTED
        dayClicked()

        trendingSwitchLabelLeft.setOnClickListener {
            dayClicked()
            for (listener in listeners) {
                listener.onSwitchStateChanged(switchState)
            }
        }
        trendingSwitchLabelRight.setOnClickListener {
            weekClicked()
            for (listener in listeners) {
                listener.onSwitchStateChanged(switchState)
            }
        }

        homeTrendingRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tvSeriesAdapter = TvSeriesAdapter({ clickedItem ->
            for (listener in listeners) {
//                listener.onTrendingTvSeriesClicked(clickedItem)
            }
        }, imageLoader)
        homeTrendingRecycler.adapter = tvSeriesAdapter
    }

    fun bindTvSeries(tvSeries: List<TvSeries>) {
        tvSeriesAdapter.bindData(tvSeries)
    }

    private fun dayClicked() {
        highlight(trendingSwitchLabelLeft, trendingSwitchLabelRight)
        switchState = TrendingSwitchState.DAY_SELECTED
    }

    private fun weekClicked() {
        highlight(trendingSwitchLabelRight, trendingSwitchLabelLeft)
        switchState = TrendingSwitchState.WEEK_SELECTED
    }

    private fun highlight(activeView: TextView, inactiveView: TextView) {
        activeView.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
        inactiveView.setBackgroundColor(Color.TRANSPARENT)
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
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_home_tvseries_trending_item, parent, false)
            return TvSeriesViewHolder(itemView)
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
            view: View
        ) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.homeTvSeriesTrendingItemTitle)
            val image: ImageView = view.findViewById(R.id.homeTvSeriesTrendingItemImage)
        }
    }
}

enum class TrendingSwitchState {
    DAY_SELECTED,
    WEEK_SELECTED
}