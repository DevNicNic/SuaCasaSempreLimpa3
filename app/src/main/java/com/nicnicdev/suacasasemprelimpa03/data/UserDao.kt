package com.nicnicdev.suacasasemprelimpa03.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser (user: UserEntity): Long

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUser ( email: String, password: String): UserEntity?
}