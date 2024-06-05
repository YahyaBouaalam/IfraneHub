package com.example.ifranehubapp.roomDB.repo

import com.example.ifranehubapp.roomDB.dao.ListingDao
import com.example.ifranehubapp.roomDB.dao.UserDao
import com.example.ifranehubapp.roomDB.entities.Listing
import com.example.ifranehubapp.roomDB.entities.User
import kotlinx.coroutines.flow.Flow

class Repository(
    private val userDAO: UserDao,
    private val listingDao: ListingDao
) {
    //listing funs
    suspend fun addListing(listing: Listing) = listingDao.addListing(listing)
    fun getallListings(): Flow<List<Listing>> = listingDao.getallListings()
    suspend fun getListing(listingid: Int) : Listing = listingDao.getListing(listingid = listingid)
    suspend fun removeListing(listingid: Int) = listingDao.deleteListing(listingid)
    suspend fun listingcount() : Int = listingDao.count()

    //user funs
    suspend fun addUser(user: User) = userDAO.addUser(user)
    suspend fun getUser(): User = userDAO.getUser()
    suspend fun count(): Int  = userDAO.count()
    suspend fun removeUser() = userDAO.deleteUser()
}