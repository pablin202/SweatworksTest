package com.pdm.sweatworkstest.di

import com.pdm.sweatworkstest.core.data.abstraction.UserRepository
import com.pdm.sweatworkstest.core.interactors.abstraction.*
import com.pdm.sweatworkstest.core.interactors.implementations.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object InteractorsModule {
    @Singleton
    @Provides
    fun provideGetUsers(
        repository: UserRepository
    ): GetUsers {
        return GetUsersImpl(repository)
    }

    @Singleton
    @Provides
    fun provideAddFavoriteUser(
        repository: UserRepository
    ): AddFavoriteUser {
        return AddFavoriteUserImpl(repository)
    }

    @Singleton
    @Provides
    fun provideGetFavoriteUsers(
        repository: UserRepository
    ): GetFavoriteUsers {
        return GetFavoriteUsersImpl(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteFavoriteUser(
        repository: UserRepository
    ): DeleteFavoriteUser {
        return DeleteFavoriteUserImpl(repository)
    }

    @Singleton
    @Provides
    fun provideGetCount(
        repository: UserRepository
    ): GetCount {
        return GetCountImpl(repository)
    }
}