package com.example.videolibrary.common.di.presentation

import com.example.videolibrary.MainActivity
import com.example.videolibrary.screens.home.HomeFragment
import com.example.videolibrary.screens.tending.TrendingFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: TrendingFragment)
}