package com.pdm.sweatworkstest.core.data.implementations

import com.pdm.sweatworkstest.core.data.abstraction.LocalDataSource
import com.pdm.sweatworkstest.core.data.abstraction.NetworkDataSource
import com.pdm.sweatworkstest.core.data.abstraction.UserRepository
import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.DataState

class UserRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) : UserRepository {
    override suspend fun getUsers(page: Int): DataState<List<User>> = networkDataSource.getUsers(page)
    override suspend fun getFavoriteUsers(): DataState<List<FavoriteUser>> = localDataSource.getFavoriteUsers()
    override suspend fun addFavoriteUser(favoriteUser: FavoriteUser) = localDataSource.addFavoriteUser(favoriteUser)
    override suspend fun deleteFavoriteUser(uuid: String) = localDataSource.deleteFavoriteUser(uuid)
    override suspend fun getCount(uuid: String): Int = localDataSource.getCount(uuid)
}