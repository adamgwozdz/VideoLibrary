package com.example.videolibrary.common.di.app

import android.app.Application
import com.example.videolibrary.common.di.RetrofitQualifier
import com.example.videolibrary.networking.TrendingRestRepository
import com.example.videolibrary.networking.UrlProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
open class AppModule(val application: Application) {

    @Provides
    @AppScope
    @RetrofitQualifier
    fun retrofit(urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlProvider.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @AppScope
    @Provides
    fun urlProvider() = UrlProvider()

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun tmdbApi(@RetrofitQualifier retrofit: Retrofit) = retrofit.create(TrendingRestRepository::class.java)
}