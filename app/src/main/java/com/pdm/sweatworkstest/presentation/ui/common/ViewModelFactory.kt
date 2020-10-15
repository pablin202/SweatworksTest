package com.pdm.sweatworkstest.presentation.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pdm.sweatworkstest.core.interactors.abstraction.*
import com.pdm.sweatworkstest.presentation.ui.details.DetailsViewModel
import com.pdm.sweatworkstest.presentation.ui.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class ViewModelFactory @Inject
constructor(private val getUsers: GetUsers, private val addFavoriteUser: AddFavoriteUser,
            private val deleteFavoriteUser: DeleteFavoriteUser,
            private val getCount: GetCount,
            private val getFavoriteUsers: GetFavoriteUsers
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){

            HomeViewModel::class.java -> {
                HomeViewModel(
                    getUsers,
                    getFavoriteUsers
                ) as T
            }

            DetailsViewModel::class.java -> {
                DetailsViewModel(
                    addFavoriteUser, deleteFavoriteUser, getCount
                ) as T
            }

            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }
}