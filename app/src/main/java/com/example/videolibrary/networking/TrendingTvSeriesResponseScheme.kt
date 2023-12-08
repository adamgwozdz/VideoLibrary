package com.example.videolibrary.networking

import com.example.videolibrary.business.trendingtvseries.TrendingTvSeries
import com.example.videolibrary.business.trendingtvseries.TvSeries
import com.google.gson.annotations.SerializedName

data class TrendingTvSeriesResponseSchema(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<TrendingTvSeriesSchema>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) {
    val trendingTvSeries: TrendingTvSeries get() = TrendingTvSeries(page, results.map { it.result }, totalPages, totalResults)
}

data class TrendingTvSeriesSchema(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("origin_country") val originCountry: List<String>
) {
    val result: TvSeries get() = TvSeries(adult, backdropPath, id, name, originalLanguage, originalName, overview,
        posterPath, mediaType, genreIds, popularity, firstAirDate, voteAverage, voteCount, originCountry)
}