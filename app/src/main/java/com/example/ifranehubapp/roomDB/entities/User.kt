package com.example.ifranehubapp.roomDB.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "userid")
    val id : String = "-1",
    @ColumnInfo(name = "name")
    val name : String = "",
    @ColumnInfo(name = "email")
    val email : String = "",
    @ColumnInfo(name = "verifiedemail")
    val emailVerified : String? = null,
    @ColumnInfo(name = "image")
    val image : String? = null,
    @ColumnInfo(name = "password")
    val hashedPassword : String? = "",
    @ColumnInfo(name = "createdAt")
    val createdAt : String = "",
    @ColumnInfo(name = "updatedAt")
    val updatedAt : String = ""
)