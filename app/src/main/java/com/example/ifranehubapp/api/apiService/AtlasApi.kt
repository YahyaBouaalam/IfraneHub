package com.example.ifranehubapp.api.apiService

import com.example.ifranehubapp.api.data.AllListingsRequest
import com.example.ifranehubapp.api.data.HostRequest
import com.example.ifranehubapp.api.data.ListingsResponse
import com.example.ifranehubapp.api.data.LoginRequest
import com.example.ifranehubapp.api.data.ReservationRequest
import com.example.ifranehubapp.api.data.ResponseHost
import com.example.ifranehubapp.api.data.ResponseReservation
import com.example.ifranehubapp.api.data.ResponseUser
import com.example.ifranehubapp.api.data.SignupRequest
import com.example.ifranehubapp.api.data.SignupResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

private const val apiKey: String = "WdWSIaiKBQyHSsvhq6849lrnwZjitI0p1LO4dLidNr97f2xNXgtHdKIj0DZ9TzuK"
private const val clientAppID: String = "data-aqskr"

interface AtlasApi {

    @POST("findOne")
    @Headers(value = [
        "apiKey: $apiKey",
        "Content-Type: application/json"
    ])
    suspend fun loginEmail(@Body body: LoginRequest) : ResponseUser

    @POST("findOne")
    @Headers(value = [
        "apiKey: $apiKey",
        "Content-Type: application/json"
    ])
    suspend fun getHost(@Body body: HostRequest) : ResponseHost

    //TODO: Fix signupEmail here and in viewmodel
    @POST("insertOne")
    @Headers(value = [
        "apiKey: $apiKey",
        "Content-Type: application/json"
    ])
    suspend fun signupEmail(@Body body: SignupRequest) : SignupResponse

    @POST("insertOne")
    @Headers(value = [
        "apiKey: $apiKey",
        "Content-Type: application/json"
    ])
    suspend fun reserve(@Body body: ReservationRequest) : ResponseReservation

    //TODO: Add getlistings, addtofavorites, etc...
    @POST("find")
    @Headers(value = [
        "apiKey: $apiKey",
        "Content-Type: application/json"
    ])
    suspend fun getlistings(@Body body: AllListingsRequest) : ListingsResponse

    @POST("insertOne")
    @Headers(value = [
        "apiKey: $apiKey",
        "Content-Type: application/json"
    ])
    suspend fun addtofavorites(@Body body: SignupRequest) : SignupResponse

    companion object {

        private var retrofitService: AtlasApi? = null
        fun getInstance() : AtlasApi {

            if (retrofitService == null) {
                retrofitService = Retrofit.Builder()
                    .baseUrl("https://eu-west-2.aws.data.mongodb-api.com/app/$clientAppID/endpoint/data/v1/action/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AtlasApi::class.java)
            }
            return retrofitService!!
        }
    }

}