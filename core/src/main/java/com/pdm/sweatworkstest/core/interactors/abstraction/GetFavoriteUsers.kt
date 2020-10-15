package com.pdm.sweatworkstest.core.interactors.abstraction

import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.util.DataState

interface GetFavoriteUsers {
    suspend operator fun invoke(): DataState<List<FavoriteUser>>
}