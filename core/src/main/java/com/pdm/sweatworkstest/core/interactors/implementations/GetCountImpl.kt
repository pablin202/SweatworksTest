package com.pdm.sweatworkstest.core.interactors.implementations

import com.pdm.sweatworkstest.core.data.abstraction.UserRepository
import com.pdm.sweatworkstest.core.interactors.abstraction.GetCount

class GetCountImpl(private val repository: UserRepository) : GetCount {
    override suspend fun invoke(uuid: String): Int = repository.getCount(uuid)
}