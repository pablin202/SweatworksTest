package com.pdm.sweatworkstest.core.domain

data class FavoriteUser(
    val uuid: String,
    val username: String,
    val titleName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val largeImage: String,
    val mediumImage: String,
    val thumbnailImage: String
)