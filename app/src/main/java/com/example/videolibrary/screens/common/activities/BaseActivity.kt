package com.example.videolibrary.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.videolibrary.MyApplication
import com.example.videolibrary.common.di.activity.ActivityComponent
import com.example.videolibrary.common.di.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        appComponent.newActivityComponentBuilder()
            .activity(this)
            .build()
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule(this))
    }

    protected val injector get() = presentationComponent
}