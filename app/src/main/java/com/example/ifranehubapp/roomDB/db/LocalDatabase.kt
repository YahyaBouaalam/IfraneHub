package com.example.ifranehubapp.roomDB.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ifranehubapp.roomDB.dao.ListingDao
import com.example.ifranehubapp.roomDB.dao.UserDao
import com.example.ifranehubapp.roomDB.entities.Listing
import com.example.ifranehubapp.roomDB.entities.User

@Database(entities = [User::class, Listing::class], version = 2, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDAO() : UserDao
    abstract fun listingDAO() : ListingDao

    companion object {
        private var INSTANCE: LocalDatabase? = null
        fun getInstance(context: Context): LocalDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, LocalDatabase::class.java, "localDB.db")
                    // add migrations here
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as LocalDatabase
        }
    }
}