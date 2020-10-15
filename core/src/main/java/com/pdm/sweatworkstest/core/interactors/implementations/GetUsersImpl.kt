package com.pdm.sweatworkstest.core.interactors.implementations

import com.pdm.sweatworkstest.core.data.abstraction.UserRepository
import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.DataState
import com.pdm.sweatworkstest.core.interactors.abstraction.GetUsers

class GetUsersImpl(private val userRepository: UserRepository) : GetUsers {
    override suspend fun invoke(page: Int): DataState<List<User>> = userRepository.getUsers(page)
}