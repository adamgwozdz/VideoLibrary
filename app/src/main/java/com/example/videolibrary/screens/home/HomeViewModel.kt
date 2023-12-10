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

    private val _tvSeries1: MutableLiveData<List<TvSeries>> = savedStateHandle.getLiveData("tvSeries1")
    val tvSeries1: LiveData<List<TvSeries>> = _tvSeries1

    init {
        viewModelScope.launch {
            val result = trendingTvSeriesUseCase.fetchWeekTrendingTv()
            if (result is TrendingTvSeriesUseCase.Result.Success) {
                _tvSeries.value = result.trendingTvSeries
            } else {
                Log.i("Logged Error", "${this.javaClass.kotlin} - fetch failed")
            }

            val result1 = trendingTvSeriesUseCase.fetchDayTrendingTv()
            if (result1 is TrendingTvSeriesUseCase.Result.Success) {
                _tvSeries1.value = result1.trendingTvSeries
            } else {
                Log.i("Logged Error", "${this.javaClass.kotlin} - fetch failed")
            }
        }
    }
}