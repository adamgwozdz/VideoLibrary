package com.example.videolibrary.screens.common

import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class ScreensNavigatorImpl @Inject constructor(private val activity: AppCompatActivity): ScreensNavigator {

    override fun navigateBack() {
        activity.onBackPressedDispatcher.onBackPressed()
    }
}