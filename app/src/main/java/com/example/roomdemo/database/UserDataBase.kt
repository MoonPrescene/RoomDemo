package com.example.roomdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.ui.models.User

@Database(entities = [User::class], version = 1)
abstract class UserDataBase: RoomDatabase() {

    abstract fun userDao(): UserDAO
    companion object {
        private var INSTANCE: UserDataBase? = null

        fun getUserDatabase(context: Context): UserDataBase? {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder<UserDataBase>(context, UserDataBase::class.java, "APPDB")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE
        }
    }
}