package com.pdm.sweatworkstest.di

import com.pdm.sweatworkstest.core.data.abstraction.LocalDataSource
import com.pdm.sweatworkstest.core.data.abstraction.NetworkDataSource
import com.pdm.sweatworkstest.core.data.abstraction.UserRepository
import com.pdm.sweatworkstest.core.data.implementations.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ): UserRepository {
        return UserRepositoryImpl(localDataSource , networkDataSource)
    }
}