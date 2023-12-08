package com.example.videolibrary.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.videolibrary.common.di.presentation.PresentationModule
import com.example.videolibrary.screens.common.activities.BaseActivity

open class BaseFragment: Fragment() {

    private val presentationComponent by lazy {
        (requireContext() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule(this)
        )
    }

    protected val injector get() = presentationComponent
}