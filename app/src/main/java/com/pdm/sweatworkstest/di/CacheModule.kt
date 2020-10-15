package com.pdm.sweatworkstest.di

import androidx.room.Room
import com.pdm.sweatworkstest.App
import com.pdm.sweatworkstest.framework.cache.database.UserDao
import com.pdm.sweatworkstest.framework.cache.database.UsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideUserDb(app: App): UsersDatabase {
        return Room.databaseBuilder(
            app,
            UsersDatabase::class.java, "user_db.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideUserDAO(usersDatabase: UsersDatabase): UserDao {
        return usersDatabase.userDao()
    }
}