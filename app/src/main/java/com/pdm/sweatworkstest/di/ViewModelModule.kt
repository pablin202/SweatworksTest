package com.pdm.sweatworkstest.di

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.pdm.sweatworkstest.core.interactors.abstraction.*
import com.pdm.sweatworkstest.presentation.ui.common.ViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
object ViewModelModule {

    @Singleton
    @Provides
    fun provideNoteViewModelFactory(
        getUsers: GetUsers,
        addFavoriteUser: AddFavoriteUser,
        deleteFavoriteUser: DeleteFavoriteUser,
        getCount: GetCount,
        getFavoriteUsers: GetFavoriteUsers
    ): ViewModelProvider.Factory{
        return ViewModelFactory(
            getUsers, addFavoriteUser, deleteFavoriteUser, getCount, getFavoriteUsers
        )
    }
}