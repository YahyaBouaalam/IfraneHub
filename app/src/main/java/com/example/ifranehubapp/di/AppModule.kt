package com.example.ifranehubapp.di

import android.app.Application
import com.example.ifranehubapp.roomDB.dao.ListingDao
import com.example.ifranehubapp.roomDB.dao.UserDao
import com.example.ifranehubapp.roomDB.db.LocalDatabase
import com.example.ifranehubapp.roomDB.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRepository(userDAO: UserDao, listingDao: ListingDao): Repository {
        return Repository(userDAO = userDAO, listingDao = listingDao)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): LocalDatabase {
        return LocalDatabase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideUserDao(database: LocalDatabase): UserDao {
        return database.userDAO()
    }

    @Singleton
    @Provides
    fun providelistingDao(database: LocalDatabase): ListingDao {
        return database.listingDAO()
    }
}