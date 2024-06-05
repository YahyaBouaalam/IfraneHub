package com.example.ifranehubapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifranehubapp.api.apiService.AtlasApi
import com.example.ifranehubapp.api.data.AllListingsRequest
import com.example.ifranehubapp.api.data.EndDate
import com.example.ifranehubapp.api.data.HostFilter
import com.example.ifranehubapp.api.data.HostRequest
import com.example.ifranehubapp.api.data.Id_
import com.example.ifranehubapp.api.data.ListingDocument
import com.example.ifranehubapp.api.data.LoginFilter
import com.example.ifranehubapp.api.data.LoginRequest
import com.example.ifranehubapp.api.data.NumberLong
import com.example.ifranehubapp.api.data.ReservationDoc
import com.example.ifranehubapp.api.data.ReservationRequest
import com.example.ifranehubapp.api.data.SignupDoc
import com.example.ifranehubapp.api.data.SignupRequest
import com.example.ifranehubapp.api.data.StartDate
import com.example.ifranehubapp.api.data.UserData
import com.example.ifranehubapp.roomDB.entities.Listing
import com.example.ifranehubapp.roomDB.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AtlasApiViewModel : ViewModel() {
    private var loginresponse : UserData by mutableStateOf(UserData("", "", "", "", "", ""))
    private var errorMessage : String by mutableStateOf("")
    private var response : String by mutableStateOf("")
    private var _newuser = MutableLiveData<User>()
    private var _loggedinuser = MutableLiveData<User>()
    private var _listing : Listing? by mutableStateOf(null)
    private var _host : User? by mutableStateOf(User())
    var listings : List<ListingDocument> by mutableStateOf(listOf())
    private var isnewuser : Boolean? by mutableStateOf(null)

    init {
        _newuser.value = User()
        _loggedinuser.value = User()
    }

    fun getListing(): Listing? {
        return _listing
    }

    // Public setter for _listing
    fun setListing(listing: Listing?) {
        _listing = listing
    }

    // Public getter for _host
    fun getHost(): User? {
        return _host
    }

    // Public setter for _host
    fun setHost(host: User?) {
        _host = host
    }

    fun resetuser() {
        _newuser.value = User()
    }
    fun findAllListings() {
        viewModelScope.launch {
            val service = withContext(Dispatchers.IO){
                AtlasApi.getInstance()
            }
            try {
                val res = service.getlistings(AllListingsRequest())
                listings = res.documents
                Log.d("listings", listings.toString())
            } catch (e:Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun findHost(id : String) {
        viewModelScope.launch {
            val service = withContext(Dispatchers.IO){
                AtlasApi.getInstance()
            }
            try {
                val res = service.getHost(HostRequest(filter = HostFilter(Id_(id))))
                Log.d("host", res.document.toString())
                _host = User(id = res.document._id, name = res.document.name, image = res.document.image)
            } catch (e:Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun loginEmail(email : String, password : String) {
        viewModelScope.launch {
            val service = withContext(Dispatchers.IO){
                AtlasApi.getInstance()
            }
            val req = LoginRequest(filter = LoginFilter(email, password))
            try {
                loginresponse = service.loginEmail(req).document
                _loggedinuser.value = User(id = loginresponse._id, name = loginresponse.name, email = loginresponse.email, emailVerified = null, image = null, hashedPassword = loginresponse.hashedPassword, createdAt = loginresponse.createdAt, updatedAt = loginresponse.updatedAt)
                Log.d("user", _loggedinuser.value.toString())
            } catch (e:Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    val newuser: LiveData<User?> get() = _newuser
    val loggedinuser : LiveData<User> get() = _loggedinuser

    fun signupEmail(email : String, password : String, name : String) {
        viewModelScope.launch {
            val service = withContext(Dispatchers.IO){
                AtlasApi.getInstance()
            }

            val doc = SignupDoc(name = name, email = email, hashedPassword = password)

            val req = SignupRequest(document = doc)
            try {
                response = service.signupEmail(req).insertedId
                _newuser.value = User(id = response, name = name, email = email, hashedPassword = password, createdAt = doc.createdAt.toString(), updatedAt =  doc.updatedAt.toString())

            } catch (e:Exception) {
                errorMessage = e.message.toString()
            }

        }
    }

    fun makereservation(start: Long, end: Long, userId: String, listingId: String = _listing!!.id, totalPrice: Int) {
        viewModelScope.launch {
            val service = withContext(Dispatchers.IO){
                AtlasApi.getInstance()
            }
            val req = ReservationRequest(document = ReservationDoc(StartDate(NumberLong(start.toString())), EndDate(NumberLong(end.toString())), Id_(userId), Id_(listingId), totalPrice))
            try {
                response = service.reserve(req).resId
            } catch (e:Exception) {
                errorMessage = e.message.toString()
            }

        }
    }
}