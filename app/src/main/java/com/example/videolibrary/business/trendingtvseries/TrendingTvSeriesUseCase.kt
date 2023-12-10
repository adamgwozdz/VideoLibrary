package com.example.videolibrary.business.trendingtvseries

import android.util.Log
import com.example.videolibrary.local.trendingtvseries.TvSeriesModel
import com.example.videolibrary.networking.TrendingRestRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingTvSeriesUseCase @Inject constructor(
    private val trendingRestRepository: TrendingRestRepository,
    private val realm: Realm
) {

    companion object {
        const val DAY = "day"
        const val WEEK = "week"
    }

    sealed class Result {
        data class Success(val trendingTvSeries: List<TvSeries>) : Result()
        data object Failure : Result()
    }

    suspend fun fetchDayTrendingTv(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = trendingRestRepository.getTrendingTv(DAY)
                if (response.isSuccessful && response.body() != null) {
                    if (isDataSaved(DAY)) {
                        return@withContext Result.Success(load(DAY))
                    } else {
                        save(response.body()!!.trendingTvSeries.results, DAY)
                        return@withContext Result.Success(response.body()!!.trendingTvSeries.results)
                    }
                } else {
                    Log.e("Logged Error", "failed - fetchWeekTrendingTv")
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    Log.e("Logged Error", "${t.message}")
                    return@withContext Result.Failure
                } else {
                    Log.e("Logged Error", "${t.message}")
                    throw t
                }
            }
        }
    }

    suspend fun fetchWeekTrendingTv(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = trendingRestRepository.getTrendingTv(WEEK)
                if (response.isSuccessful && response.body() != null) {
                    if (isDataSaved(WEEK)) {
                        return@withContext Result.Success(load(WEEK))
                    } else {
                        save(response.body()!!.trendingTvSeries.results, WEEK)
                        return@withContext Result.Success(response.body()!!.trendingTvSeries.results)
                    }
                } else {
                    Log.e("Logged Error", "failed - fetchWeekTrendingTv")
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    Log.e("Logged Error", "${t.message}")
                    return@withContext Result.Failure
                } else {
                    Log.e("Logged Error", "${t.message}")
                    throw t
                }
            }
        }
    }

    private fun save(tvSeries: List<TvSeries>, tag: String) {
        val tvSeriesList = mutableListOf<TvSeriesModel>()
        var prefix = 0
        when (tag) {
            DAY -> prefix = 0
            WEEK -> prefix = 1
        }
        tvSeries.forEach {
            tvSeriesList.add(TvSeriesModel().apply {
                val genreIdsRealmList = realmListOf<Int>()
                val originCountryRealmList = realmListOf<String>()
                genreIdsRealmList.addAll(it.genreIds)
                originCountryRealmList.addAll(it.originCountry)
                id = prefix + it.id
                adult = it.adult
                backdropPath = it.backdropPath
                name = it.name
                originalLanguage = it.originalLanguage
                originalName = it.originalName
                overview = it.overview
                posterPath = it.posterPath
                mediaType = it.mediaType
                genreIds = genreIdsRealmList
                popularity = it.popularity
                firstAirDate = it.firstAirDate
                voteAverage = it.voteAverage
                voteCount = it.voteCount
                originCountry = originCountryRealmList
                this.tag = tag
            })
        }

        realm.writeBlocking {
            for (tvSeriesItem in tvSeriesList) {
                copyToRealm(instance = tvSeriesItem, updatePolicy = UpdatePolicy.ERROR)
            }
        }
    }

    private fun load(tag: String): List<TvSeries> {
        val tvSeriesList = mutableListOf<TvSeries>()
        realm.query<TvSeriesModel>("tag = $0", tag).find().forEach {
            tvSeriesList.add(
                TvSeries(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    name = it.name,
                    originalLanguage = it.originalLanguage,
                    originalName = it.originalName,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    mediaType = it.mediaType,
                    genreIds = it.genreIds,
                    popularity = it.popularity,
                    firstAirDate = it.firstAirDate,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    originCountry = it.originCountry,
                )
            )
        }
        return tvSeriesList
    }

    private fun isDataSaved(tag: String): Boolean {
        return realm.query<TvSeriesModel>("tag = $0", tag).count().find() > 0
    }
}