package com.pdm.sweatworkstest.di

import androidx.lifecycle.ViewModelProvider
import com.pdm.sweatworkstest.presentation.ui.common.FragmentFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
object FragmentFactoryModule {

    @Singleton
    @Provides
    fun provideFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory
    ): FragmentFactory {
        return FragmentFactory (
            viewModelFactory
        )
    }
}