package com.example.videolibrary.screens.tending

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.videolibrary.business.tvseries.TvSeriesUseCase
import javax.inject.Inject

class TrendingViewModel @Inject constructor(
    private val tvSeriesUseCase: TvSeriesUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    init {

    }
}