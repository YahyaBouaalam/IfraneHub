package com.example.ifranehubapp.api.data

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.RealmInstant

data class HostRequest(
    val dataSource: String = "Cluster0",
    val database: String = "test",
    val collection: String = "User",
    val filter: HostFilter
)

data class HostFilter(
    val _id: Id_
)

data class Id_(
    @SerializedName("\$oid")
    val oid: String
)

data class LoginRequest(
    val dataSource: String = "Cluster0",
    val database: String = "test",
    val collection: String = "User",
    val filter: LoginFilter
)

data class LoginFilter(
    val email: String,
    val hashedPassword: String
)

data class SignupRequest(
    val dataSource: String = "Cluster0",
    val database: String = "test",
    val collection: String = "User",
    val document: SignupDoc
)

data class ReservationRequest(
    val dataSource: String = "Cluster0",
    val database: String = "test",
    val collection: String = "Reservation",
    val document: ReservationDoc

)

data class ReservationDoc(
    val startDate : StartDate,
    val endDate : EndDate,
    val userId : Id_,
    val listingId : Id_,
    val totalPrice : Int,
    val createdAt : CreatedAt = CreatedAt()
)

data class SignupDoc (
    val name : String = "",
    val email : String = "",
    val emailVerified : RealmInstant? = null,
    val image : String? = null,
    val hashedPassword : String = "",
    val createdAt : CreatedAt = CreatedAt(),
    val updatedAt : UpdatedAt = UpdatedAt()
//    val createdAt : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")),
//    val updatedAt : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
)

data class CreatedAt(
    @SerializedName("\$date")
    val date: NumberLong = NumberLong()
)

data class StartDate(
    @SerializedName("\$date")
    val date: NumberLong
)
data class EndDate(
    @SerializedName("\$date")
    val date: NumberLong
)

data class UpdatedAt(
    @SerializedName("\$date")
    val date: NumberLong = NumberLong()
)

data class NumberLong(
    @SerializedName("\$numberLong")
    val long: String = ((instant.epochSeconds.times(1000)).plus(instant.nanosecondsOfSecond/1000000)).toString()
)
data class AllListingsRequest(
    val dataSource: String = "Cluster0",
    val database: String = "test",
    val collection: String = "Listing"
)

val instant = RealmInstant.now()
