package com.example.videolibrary.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.videolibrary.business.trendingtvseries.TrendingTvSeriesUseCase
import com.example.videolibrary.business.trendingtvseries.TvSeries
import com.example.videolibrary.screens.viewmodels.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val trendingTvSeriesUseCase: TrendingTvSeriesUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val _tvSeries: MutableLiveData<List<TvSeries>> = savedStateHandle.getLiveData("tvSeries")
    val tvSeries: LiveData<List<TvSeries>> = _tvSeries

    init {
        viewModelScope.launch {
            val result = trendingTvSeriesUseCase.fetchWeekTrendingTv()
            if (result is TrendingTvSeriesUseCase.Result.Success) {
                _tvSeries.value = result.trendingTvSeries
            } else {
                Log.i("Api Error", "${this.javaClass.kotlin} fetch failed")
            }
        }
    }
}