package com.example.databasedemo

import com.example.databasedemo.database.User

interface UserDeleteListener {
    fun deleteUser(user: User)
}