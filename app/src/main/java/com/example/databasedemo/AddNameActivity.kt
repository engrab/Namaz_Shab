package com.example.databasedemo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databasedemo.database.User
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNameActivity : AppCompatActivity(), UserDeleteListener {
    private lateinit var etName: TextInputEditText
    private lateinit var viewModel: UserViewModel
    private lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_name)

        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,UserViewModelFactory(
            (application as App).db
        )).get(UserViewModel::class.java)
        etName = findViewById(R.id.etName)

        findViewById<Button>(R.id.btnSave).setOnClickListener {

            val name = etName.text.toString()
            if (name.isBlank()){
                Toast.makeText(this@AddNameActivity, "Please Enter Name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            CoroutineScope(Dispatchers.Default).launch {
                viewModel.setUser(User(0, name))
                withContext(Dispatchers.Main){
                    Toast.makeText(this@AddNameActivity, "Successfully Add Name", Toast.LENGTH_LONG).show()

                }

            }

        }

        viewModel.getAllUser().observe(this) {
            rv.adapter = DeleteUserAdapter(this, it, this)

        }
    }

    override fun deleteUser(user: User) {
        CoroutineScope(Dispatchers.Default).launch {
            viewModel.db.userDao().deleteUser(user)

        }

    }
}