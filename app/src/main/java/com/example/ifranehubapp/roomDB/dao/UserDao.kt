package com.example.ifranehubapp.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ifranehubapp.roomDB.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table")
    suspend fun getUser(): User

    @Query("SELECT Count(*) FROM user_table")
    suspend fun count(): Int

    @Query("DELETE FROM user_table")
    suspend fun deleteUser()

//    @Query("UPDATE user_table SET username = :newusername")
//    suspend fun updateUser(newusername: String)

}