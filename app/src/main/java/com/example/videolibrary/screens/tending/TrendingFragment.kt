package com.example.videolibrary.screens.tending

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.videolibrary.screens.common.ScreensNavigator
import com.example.videolibrary.screens.common.dialogs.DialogsNavigator
import com.example.videolibrary.screens.common.fragments.BaseFragment
import com.example.videolibrary.screens.common.views.ViewFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import javax.inject.Inject

class TrendingFragment: BaseFragment(), TrendingView.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Inject lateinit var dialogsNavigator: DialogsNavigator
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var viewFactory: ViewFactory

    private lateinit var view: TrendingView

    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = viewFactory.newTrendingView(container)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        view.registerListener(this)
        if (!isDataLoaded) {
            fetchQuestions()
        }
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        view.unregisterListener(this)
    }

    override fun onRefreshClicked() {
        fetchQuestions()
    }

    private fun fetchQuestions() {
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }
}