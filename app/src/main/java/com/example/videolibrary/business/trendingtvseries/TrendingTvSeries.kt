package com.example.videolibrary.business.trendingtvseries

import java.io.Serializable

data class TrendingTvSeries(
    val page: Int,
    val results: List<TvSeries>,
    val totalPages: Int,
    val totalResults: Int
): Serializable

data class TvSeries(
    val adult: Boolean,
    val backdropPath: String?,
    val id: Long,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val posterPath: String?,
    val mediaType: String,
    val genreIds: List<Int>,
    val popularity: Double,
    val firstAirDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val originCountry: List<String>
): Serializable