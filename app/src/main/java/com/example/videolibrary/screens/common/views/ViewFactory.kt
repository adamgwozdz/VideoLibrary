package com.example.videolibrary.screens.common.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.videolibrary.screens.common.imageloader.ImageLoader
import com.example.videolibrary.screens.home.HomeView
import com.example.videolibrary.screens.tending.TrendingView
import javax.inject.Inject
import javax.inject.Provider

class ViewFactory @Inject constructor(
    private val layoutInflater: Provider<LayoutInflater>,
    private val imageLoaderProvider: Provider<ImageLoader>,
    private val activity: Provider<AppCompatActivity>
) {

    fun newHomeView(parent: ViewGroup?): HomeView {
        return HomeView(layoutInflater.get(), parent, imageLoaderProvider.get())
    }

    fun newTrendingView(parent: ViewGroup?): TrendingView {
        return TrendingView(layoutInflater.get(), parent)
    }
}