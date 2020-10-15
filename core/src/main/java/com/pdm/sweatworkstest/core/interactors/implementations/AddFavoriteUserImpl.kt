package com.pdm.sweatworkstest.core.interactors.implementations

import com.pdm.sweatworkstest.core.data.abstraction.UserRepository
import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.interactors.abstraction.AddFavoriteUser

class AddFavoriteUserImpl(private val repository: UserRepository) : AddFavoriteUser {
    override suspend fun invoke(favoriteUser: FavoriteUser): Long = repository.addFavoriteUser(favoriteUser)
}