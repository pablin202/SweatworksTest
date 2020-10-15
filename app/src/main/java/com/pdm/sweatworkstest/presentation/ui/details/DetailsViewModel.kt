package com.pdm.sweatworkstest.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.DataState
import com.pdm.sweatworkstest.core.interactors.abstraction.AddFavoriteUser
import com.pdm.sweatworkstest.core.interactors.abstraction.DeleteFavoriteUser
import com.pdm.sweatworkstest.core.interactors.abstraction.GetCount
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class DetailsViewModel
@Inject constructor(
    private val addFavoriteUser: AddFavoriteUser,
    private val deleteFavoriteUser: DeleteFavoriteUser,
    private val getCount: GetCount
) : ViewModel() {

    private val _isFavorite: MutableLiveData<Int> =
        MutableLiveData()
    val isFavorite: LiveData<Int>
        get() = _isFavorite

    fun userIsFavorite(uuid: String) {
        viewModelScope.launch {
            _isFavorite.value = getCount(uuid)
        }
    }

    fun addFavoriteUser(
        uuid: String,
        username :String,
        titleName: String,
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        largeImage: String,
        mediumImage: String,
        thumbnailImage: String
    ): Boolean {
        return try {
            viewModelScope.launch {
                addFavoriteUser(
                    FavoriteUser(
                        uuid,
                        username,
                        titleName,
                        firstName,
                        lastName,
                        email,
                        phone,
                        largeImage,
                        mediumImage,
                        thumbnailImage
                    )
                )
                userIsFavorite(uuid)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteFromFavoriteUser(uuid: String) {
        viewModelScope.launch {
            deleteFavoriteUser(uuid)
            userIsFavorite(uuid)
        }
    }
}