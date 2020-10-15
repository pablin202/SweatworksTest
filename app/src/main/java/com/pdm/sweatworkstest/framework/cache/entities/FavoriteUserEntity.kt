package com.pdm.sweatworkstest.framework.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_users")
data class FavoriteUserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "title_name")
    val titleName: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "large_image")
    val largeImage: String,
    @ColumnInfo(name = "medium_image")
    val mediumImage: String,
    @ColumnInfo(name = "thumbnail_image")
    val thumbnailImage: String
)