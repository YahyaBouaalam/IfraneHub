package com.example.ifranehubapp.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ifranehubapp.roomDB.entities.Listing
import com.example.ifranehubapp.roomDB.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListing(listing: Listing)

    @Query("SELECT * FROM listings")
    fun getallListings(): Flow<List<Listing>>

    @Query("SELECT * FROM listings WHERE id = :listingid")
    suspend fun getListing(listingid : Int): Listing

    @Query("SELECT Count(*) FROM listings")
    suspend fun count(): Int

    @Query("DELETE FROM listings WHERE id = :listingid")
    suspend fun deleteListing(listingid : Int)

}