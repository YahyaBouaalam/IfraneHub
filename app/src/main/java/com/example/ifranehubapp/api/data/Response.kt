package com.example.ifranehubapp.api.data

data class ResponseUser(
    val document: UserData
)

data class UserData(
    val _id: String,
    val name: String,
    val email: String,
    val hashedPassword: String,
    val createdAt: String,
    val updatedAt: String
)

data class ResponseReservation(
    val resId: String
)

data class ResponseHost(
    val document: HostData
)

data class HostData(
    val _id: String,
    val name: String,
    val image: String
)

data class SignupResponse(
    val insertedId: String
)

data class ListingsResponse(
    val documents: List<ListingDocument>
)

data class ListingDocument(
    val _id: String,
    val title: String,
    val description: String,
    val imageSrc: String,
    val createdAt: String,
    val category: String,
    val roomCount: Int,
    val bathroomCount: Int,
    val guestCount: Int,
    val location: String,
    val userId: String,
    val price: Int
)