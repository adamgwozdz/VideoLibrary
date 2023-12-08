package com.example.videolibrary

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.videolibrary.business.tvseries.TvSeriesUseCase
import com.example.videolibrary.databinding.LayoutFrameBinding
import com.example.videolibrary.screens.common.ScreensNavigator
import com.example.videolibrary.screens.common.activities.BaseActivity
import com.example.videolibrary.screens.home.HomeFragment
import com.example.videolibrary.screens.tending.TrendingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView.LABEL_VISIBILITY_UNLABELED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity: BaseActivity() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Inject lateinit var tvSeriesUseCase: TvSeriesUseCase
    @Inject lateinit var screensNavigator: ScreensNavigator

    private lateinit var currentFragment: Fragment

    private lateinit var binding: LayoutFrameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.layout_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_content, TrendingFragment())
                .commit()
        }

        currentFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_content, currentFragment)
            .commit()

        binding.bottomNavigationView.labelVisibilityMode = LABEL_VISIBILITY_UNLABELED
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> showFragment(HomeFragment())
                R.id.navigation_trending -> showFragment(TrendingFragment())
            }
            true
        }
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun showFragment(fragment: Fragment) {
        if (fragment != currentFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_content, fragment)
                .commit()
            currentFragment = fragment
        }
    }

    private fun onFetchFailed() {
        Log.e("Api Error", "${this.javaClass.kotlin} - fetch failed")
    }
}