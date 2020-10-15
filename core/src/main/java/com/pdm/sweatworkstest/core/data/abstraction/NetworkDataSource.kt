package com.pdm.sweatworkstest.core.data.abstraction

import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.DataState

interface NetworkDataSource {
    suspend fun getUsers(page: Int) : DataState<List<User>>
}