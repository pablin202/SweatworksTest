package com.pdm.sweatworkstest.core.interactors.abstraction

import com.pdm.sweatworkstest.core.domain.FavoriteUser
import com.pdm.sweatworkstest.core.domain.User
import com.pdm.sweatworkstest.core.domain.util.DataState

interface AddFavoriteUser {
    suspend operator fun invoke(favoriteUser: FavoriteUser): Long
}