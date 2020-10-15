package com.pdm.sweatworkstest.core.interactors.abstraction

import com.pdm.sweatworkstest.core.domain.FavoriteUser
import java.util.*

interface DeleteFavoriteUser {
    suspend operator fun invoke(uuid: String): Int
}