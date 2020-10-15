package com.pdm.sweatworkstest.di

import com.pdm.sweatworkstest.App
import com.pdm.sweatworkstest.presentation.ui.MainActivity
import com.pdm.sweatworkstest.presentation.ui.details.DetailsFragment
import com.pdm.sweatworkstest.presentation.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [CacheModule::class, NetworkModule::class, DataSourceModule::class, InteractorsModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(detailsFragment: DetailsFragment)
}