package com.example.videolibrary.screens.viewmodels

import android.app.Application
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.videolibrary.MyApplication
import com.example.videolibrary.business.trendingtvseries.TrendingTvSeriesUseCase
import com.example.videolibrary.business.tvseries.TvSeriesUseCase
import com.example.videolibrary.screens.home.HomeViewModel
import com.example.videolibrary.screens.tending.TrendingViewModel
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val tvSeriesUseCaseProvider: Provider<TvSeriesUseCase>,
    private val trendingTvSeriesUseCaseProvider: Provider<TrendingTvSeriesUseCase>,
    savedStateRegistryOwner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when(modelClass) {
            HomeViewModel::class.java -> HomeViewModel(
                trendingTvSeriesUseCaseProvider.get(),
                handle,
            ) as T
            TrendingViewModel::class.java -> TrendingViewModel(
                tvSeriesUseCaseProvider.get(),
                handle
            ) as T
            else -> throw RuntimeException("Unsupported viewmodel type: $modelClass")
        }
    }
}