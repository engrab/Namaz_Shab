package com.example.databasedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.databasedemo.database.User
import com.example.databasedemo.database.UserDataBase

class UserViewModel(val db: UserDataBase) : ViewModel() {


    fun getAllUser(): LiveData<List<User>> = db.userDao().getAllUser()
    fun setUser(user: User) = db.userDao().setUser(user)

}

class UserViewModelFactory(val db: UserDataBase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(db) as T
    }
}