package com.example.videolibrary.screens.tending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.videolibrary.R
import com.example.videolibrary.screens.common.views.BaseView

class TrendingView(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): BaseView<TrendingView.Listener>(
    layoutInflater,
    parent,
    R.layout.layout_trending_view
) {

    interface Listener {
        fun onRefreshClicked()
    }

    private val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.trendingSwipeRefresh)
    private val recyclerView: RecyclerView

    init {
        // init pull-down-to-refresh
        swipeRefresh.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }

        // init recycler view
        recyclerView = findViewById(R.id.trendingRecycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }
}