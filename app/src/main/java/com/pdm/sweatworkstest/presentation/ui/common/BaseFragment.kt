package com.pdm.sweatworkstest.presentation.ui.common

import android.content.Context
import androidx.fragment.app.Fragment
import com.pdm.sweatworkstest.App
import com.pdm.sweatworkstest.di.AppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BaseFragment : Fragment() {

    abstract fun inject()

    fun getAppComponent(): AppComponent {
        return activity?.run {
            (application as App).appComponent
        }?: throw Exception("AppComponent is null.")
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }
}