package com.example.videolibrary.business.trendingtvseries

import com.example.videolibrary.networking.TrendingRestRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingTvSeriesUseCase @Inject constructor(private val trendingRestRepository: TrendingRestRepository) {

    sealed class Result {
        data class Success(val trendingTvSeries: List<TvSeries>) : Result()
        data object Failure : Result()
    }

    suspend fun fetchDayTrendingTv(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = trendingRestRepository.getTrendingTv("day")
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.trendingTvSeries.results)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }

    suspend fun fetchWeekTrendingTv(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = trendingRestRepository.getTrendingTv("week")
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.trendingTvSeries.results)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}