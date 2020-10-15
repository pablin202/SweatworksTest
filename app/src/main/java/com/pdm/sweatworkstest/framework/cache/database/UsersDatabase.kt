package com.pdm.sweatworkstest.framework.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pdm.sweatworkstest.framework.cache.entities.FavoriteUserEntity

@Database(entities = [FavoriteUserEntity::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}