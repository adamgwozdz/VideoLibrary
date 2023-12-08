package com.example.videolibrary

import android.app.Application
import com.example.videolibrary.common.di.app.AppComponent
import com.example.videolibrary.common.di.app.AppModule
import com.example.videolibrary.common.di.app.DaggerAppComponent

class MyApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}