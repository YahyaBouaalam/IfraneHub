package com.example.ifranehubapp.roomDB.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase

@Entity(tableName = "listings")
data class Listing(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id : String,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image")
    val imageSrc : String,
    @ColumnInfo(name = "createdAt")
    val createdAt : String,
    @ColumnInfo(name = "category")
    val category : String,
    @ColumnInfo(name = "rooms")
    val roomCount : Int,
    @ColumnInfo(name = "bathrooms")
    val bathroomCount : Int,
    @ColumnInfo(name = "guests")
    val guestCount : Int,
    @ColumnInfo(name = "location")
    val locationValue : String,
    @ColumnInfo(name = "userid")
    val userId : String,
    @ColumnInfo(name = "username")
    val name : String,
    @ColumnInfo(name = "price")
    val price : Int
)