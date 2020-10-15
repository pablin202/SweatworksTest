package com.pdm.sweatworkstest.core.interactors.abstraction

interface GetCount {
    suspend operator fun invoke(uuid: String): Int
}