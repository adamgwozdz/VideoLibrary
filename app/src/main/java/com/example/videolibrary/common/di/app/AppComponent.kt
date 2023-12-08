package com.example.videolibrary.common.di.app

import com.example.videolibrary.common.di.activity.ActivityComponent
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder
}