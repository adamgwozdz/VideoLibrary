package com.example.videolibrary.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.example.videolibrary.common.di.presentation.PresentationModule
import com.example.videolibrary.screens.common.activities.BaseActivity

open class BaseDialog: DialogFragment() {

    private val presentationComponent by lazy {
        (requireContext() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule(this)
        )
    }

    protected val injector get() = presentationComponent
}