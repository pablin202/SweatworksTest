package com.pdm.sweatworkstest.core.interactors.abstraction

import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.DataState

interface GetUsers {
    suspend operator fun invoke(page: Int): DataState<List<User>>
}