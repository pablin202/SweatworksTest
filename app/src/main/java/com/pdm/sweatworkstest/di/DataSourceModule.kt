package com.pdm.sweatworkstest.di

import com.pdm.sweatworkstest.core.data.abstraction.LocalDataSource
import com.pdm.sweatworkstest.core.data.abstraction.NetworkDataSource
import com.pdm.sweatworkstest.framework.cache.database.UserDao
import com.pdm.sweatworkstest.framework.cache.implementations.RoomDataSourceImpl
import com.pdm.sweatworkstest.framework.cache.mappers.FavoriteUserMapper
import com.pdm.sweatworkstest.framework.network.ApiService
import com.pdm.sweatworkstest.framework.network.implementations.NetworkDataSourceImpl
import com.pdm.sweatworkstest.framework.network.mappers.UserMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataSourceModule {
    @Singleton
    @Provides
    fun provideNetworkDataSource(
        networkService: ApiService,
        userMapper: UserMapper
    ): NetworkDataSource {
        return NetworkDataSourceImpl(networkService, userMapper)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        userDao: UserDao,
        favoriteUserMapper: FavoriteUserMapper
    ): LocalDataSource {
        return RoomDataSourceImpl(userDao, favoriteUserMapper)
    }
}