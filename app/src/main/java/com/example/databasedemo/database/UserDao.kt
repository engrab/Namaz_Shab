package com.example.databasedemo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUser(user: User)

    @Query("SELECT * from user")
    fun getAllUser():LiveData<List<User>>

    @Query("DELETE FROM user")
    fun deleteAll()

    @Delete
    fun deleteUser(user: User)
}