package com.pdm.sweatworkstest

import android.app.Application
import com.pdm.sweatworkstest.di.AppComponent
import com.pdm.sweatworkstest.di.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
open class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    open fun initAppComponent(){
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
    }
}