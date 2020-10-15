package com.pdm.sweatworkstest.core.interactors.implementations

import com.pdm.sweatworkstest.core.data.abstraction.UserRepository
import com.pdm.sweatworkstest.core.interactors.abstraction.DeleteFavoriteUser

class DeleteFavoriteUserImpl(private val repository: UserRepository) : DeleteFavoriteUser {
    override suspend fun invoke(uuid: String): Int = repository.deleteFavoriteUser(uuid)
}