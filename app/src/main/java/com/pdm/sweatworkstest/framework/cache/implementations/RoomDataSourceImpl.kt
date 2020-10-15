package com.pdm.sweatworkstest.framework.cache.implementations

import com.pdm.sweatworkstest.core.data.abstraction.LocalDataSource
import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.util.DataState
import com.pdm.sweatworkstest.framework.cache.database.UserDao
import com.pdm.sweatworkstest.framework.cache.database.UsersDatabase
import com.pdm.sweatworkstest.framework.cache.mappers.FavoriteUserMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomDataSourceImpl(
    private val userDao: UserDao,
    private val favoriteUserMapper: FavoriteUserMapper
) : LocalDataSource {

    override suspend fun getFavoriteUsers(): DataState<List<FavoriteUser>> {
        return try {

            DataState.Success(favoriteUserMapper.mapFromEntityList(userDao.selectUsers()))

        } catch (e: java.lang.Exception) {
            DataState.Error(e)
        }
    }

    override suspend fun addFavoriteUser(favoriteUser: FavoriteUser): Long {
        return try {

            userDao.insertUser(favoriteUserMapper.mapToEntity(favoriteUser))

        } catch (e: java.lang.Exception) {
            throw Exception(e)
        }
    }

    override suspend fun deleteFavoriteUser(uuid: String): Int {
        return try {

            userDao.deleteUser(uuid)

        } catch (e: java.lang.Exception) {
            throw Exception(e)
        }
    }

    override suspend fun getCount(uuid: String): Int {
        return try {

            userDao.getCount(uuid)

        } catch (e: java.lang.Exception) {
            throw Exception(e)
        }
    }
}