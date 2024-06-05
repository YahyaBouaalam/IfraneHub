package com.example.ifranehubapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ifranehubapp.roomDB.entities.Listing
import com.example.ifranehubapp.roomDB.entities.User
import com.example.ifranehubapp.roomDB.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DBViewmodelAbstract {
    val selectedListingState: State<Listing?>
    val loggedIn: State<Boolean>
    fun selectListing(listing: Listing)
    fun resetSelectedListing()
    fun setAuthTrue()
    fun setAuthFalse()
    suspend fun getUser(): User
    fun adduser(user: User)
    suspend fun usercount() : Int
    fun removeUser()
    val ListingListFlow: Flow<List<Listing>>
    fun addListing(listing: Listing)
    fun removeListing(listingid: Int)
    suspend fun listingcount() : Int
}

@HiltViewModel
class DBViewmodel @Inject constructor(private val repo: Repository) : ViewModel(), DBViewmodelAbstract {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val _loggedIn = mutableStateOf(false)
    override val loggedIn: State<Boolean>
        get() = _loggedIn

    private val _selectedListingState: MutableState<Listing?> = mutableStateOf(null)
    override val selectedListingState: State<Listing?>
        get() = _selectedListingState

    override val ListingListFlow: Flow<List<Listing>> = repo.getallListings()

    override suspend fun getUser(): User {
        return withContext(Dispatchers.IO) {
            repo.getUser()
        }
    }

    override fun adduser(user: User) {
        ioScope.launch {
            repo.addUser(user)
        }
    }

    override suspend fun usercount(): Int {
        return withContext(Dispatchers.IO) {
            repo.count()
        }
    }

    override fun selectListing(listing: Listing) {
        _selectedListingState.value = listing
    }

    override fun resetSelectedListing() {
        _selectedListingState.value = null
    }

    override fun setAuthFalse() {
        _loggedIn.value = false
    }

    override fun setAuthTrue() {
        _loggedIn.value = true
    }

    override fun removeUser() {
        ioScope.launch {
            repo.removeUser()
        }
    }

    override fun addListing(listing: Listing) {
        ioScope.launch {
            repo.addListing(listing)
        }
    }

    override fun removeListing(listingid: Int) {
        ioScope.launch {
            repo.removeListing(listingid= listingid)
        }
    }

    override suspend fun listingcount() : Int {
        return withContext(Dispatchers.IO) {
            repo.listingcount()
        }
    }

}