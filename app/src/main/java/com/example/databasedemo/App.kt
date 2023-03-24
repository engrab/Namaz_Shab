package com.example.databasedemo

import android.app.Application
import com.example.databasedemo.database.UserDataBase

class App:Application() {


    val db:UserDataBase by lazy {
        UserDataBase.getDatabase(this)
    }

}