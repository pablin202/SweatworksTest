package com.pdm.sweatworkstest.core.interactors.implementations

import com.pdm.sweatworkstest.core.data.abstraction.UserRepository
import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.util.DataState
import com.pdm.sweatworkstest.core.interactors.abstraction.GetFavoriteUsers

class GetFavoriteUsersImpl(private val repository: UserRepository) : GetFavoriteUsers {
    override suspend fun invoke(): DataState<List<FavoriteUser>> = repository.getFavoriteUsers()
}