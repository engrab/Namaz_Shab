package com.example.databasedemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDataBase:RoomDatabase() {

    abstract fun userDao():UserDao

   companion object{

       @Volatile
       private var INSTANCE:UserDataBase? = null
       fun getDatabase(context: Context):UserDataBase{
           val tempInstance = INSTANCE
           if (tempInstance != null){
               return tempInstance
           }
           synchronized(this){
               val instance = Room.databaseBuilder(context.applicationContext,
               UserDataBase::class.java,
               "userDB.db").build()
               INSTANCE = instance
               return instance
           }
       }
   }
}
