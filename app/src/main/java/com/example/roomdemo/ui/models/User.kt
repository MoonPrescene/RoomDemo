package com.example.roomdemo.ui.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    //getter và setter đã được tạo tự động
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "Name")
    val userName: String,

    @ColumnInfo(name = "Address")
    val userAddress: String,
)