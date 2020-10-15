package com.pdm.sweatworkstest.framework.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.sweatworkstest.framework.cache.entities.FavoriteUserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: FavoriteUserEntity): Long

    @Query("DELETE FROM favorite_users WHERE uuid = :primaryKey")
    suspend fun deleteUser(primaryKey: String): Int

    @Query("SELECT * FROM favorite_users")
    suspend fun selectUsers(): List<FavoriteUserEntity>

    @Query("SELECT COUNT(*) FROM favorite_users WHERE uuid = :primaryKey")
    suspend fun getCount(primaryKey: String): Int
}