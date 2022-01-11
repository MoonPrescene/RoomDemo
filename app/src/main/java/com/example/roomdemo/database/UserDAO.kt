package com.example.roomdemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdemo.ui.models.User

@Dao
interface UserDAO {

    @Insert
    fun insertUser(vararg user: User)

    @Query("SELECT * FROM user")
    fun getListUser(): List<User>

    @Query("DELETE FROM user")
    fun deleteAll()
}