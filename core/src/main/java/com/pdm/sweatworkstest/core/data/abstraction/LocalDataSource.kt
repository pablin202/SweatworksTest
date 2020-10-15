package com.pdm.sweatworkstest.core.data.abstraction

import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.util.DataState

interface LocalDataSource {
    suspend fun getFavoriteUsers() : DataState<List<FavoriteUser>>
    suspend fun addFavoriteUser(favoriteUser: FavoriteUser) : Long
    suspend fun deleteFavoriteUser(uuid: String) : Int
    suspend fun getCount(uuid: String) : Int
}