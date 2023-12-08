package com.example.videolibrary.networking

import com.example.videolibrary.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TrendingRestRepository {

    /**
     * timeWindow - "day" or "week"
     */
    @GET("trending/tv/{time_window}?api_key=${Constants.API_KEY}")
    suspend fun getTrendingTv(
        @Path("time_window") timeWindow: String
    ): Response<TrendingTvSeriesResponseSchema>
}