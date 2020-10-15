package com.pdm.sweatworkstest.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.DataState
import com.pdm.sweatworkstest.core.interactors.abstraction.GetFavoriteUsers
import com.pdm.sweatworkstest.core.interactors.abstraction.GetUsers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class HomeViewModel
@Inject constructor(private val getUsers: GetUsers, private val getFavoriteUsers: GetFavoriteUsers) : ViewModel() {

    // Users
    private var currentPage = 0
    private val _dataStateUsers: MutableLiveData<DataState<List<User>>> =
        MutableLiveData()
    val dataStateUsers: LiveData<DataState<List<User>>>
        get() = _dataStateUsers

    private val _dataStateFavoriteUsers: MutableLiveData<DataState<List<FavoriteUser>>> =
        MutableLiveData()
    val dataStateFavoriteUsers: LiveData<DataState<List<FavoriteUser>>>
        get() = _dataStateFavoriteUsers

    fun setStateEvent(
        mainStateEvent: MainStateEvent
    ) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetUsers -> {
                    _dataStateUsers.value = DataState.Loading
                    currentPage += 1
                    _dataStateUsers.value = getUsers(currentPage)
                }
                is MainStateEvent.GetFavoriteUsers -> {
                    _dataStateFavoriteUsers.value = DataState.Loading
                    _dataStateFavoriteUsers.value = getFavoriteUsers()
                }

                MainStateEvent.None -> {
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetUsers : MainStateEvent()
    object GetFavoriteUsers : MainStateEvent()
    object None : MainStateEvent()
}