package com.example.videolibrary.screens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.videolibrary.screens.common.ScreensNavigator
import com.example.videolibrary.screens.common.dialogs.DialogsNavigator
import com.example.videolibrary.screens.common.fragments.BaseFragment
import com.example.videolibrary.screens.common.views.ViewFactory
import com.example.videolibrary.screens.tending.TrendingView
import com.example.videolibrary.screens.viewmodels.ViewModelFactory
import javax.inject.Inject

class HomeFragment(): BaseFragment(), HomeView.Listener {

    @Inject lateinit var dialogsNavigator: DialogsNavigator
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var viewFactory: ViewFactory
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var view: HomeView
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        container?.removeAllViews()
        view = viewFactory.newHomeView(container)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        getTrendingTvSeries()
        getTrendingTvSeries1()
    }

    override fun onStop() {
        super.onStop()
        view.unregisterListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    private fun getTrendingTvSeries() {
        viewModel.tvSeries.observe(this) { tvSeries -> view.bindTvSeries(tvSeries) }
    }

    private fun getTrendingTvSeries1() {
        viewModel.tvSeries1.observe(this) { tvSeries -> view.bindTvSeries1(tvSeries) }
    }
}