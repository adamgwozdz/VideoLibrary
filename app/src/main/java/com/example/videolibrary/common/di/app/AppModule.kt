package com.example.videolibrary.common.di.app

import android.app.Application
import com.example.videolibrary.common.di.RetrofitQualifier
import com.example.videolibrary.local.trendingtvseries.TvSeriesModel
import com.example.videolibrary.networking.TrendingRestRepository
import com.example.videolibrary.networking.UrlProvider
import dagger.Module
import dagger.Provides
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
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

    @Provides
    @AppScope
    fun realm() = Realm.open(detailedConfig())

    private fun detailedConfig(): RealmConfiguration {
        return RealmConfiguration.Builder(
            schema = setOf(TvSeriesModel::class)
        )
            .deleteRealmIfMigrationNeeded()
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