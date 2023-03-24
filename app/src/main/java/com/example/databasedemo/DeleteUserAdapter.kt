package com.example.databasedemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.databasedemo.database.User

class DeleteUserAdapter(val context: Context, val list: List<User>, val listener:UserDeleteListener) :
    RecyclerView.Adapter<DeleteUserAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val ivDelete: ImageView = view.findViewById(R.id.ivDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.delete_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = list.get(position)
        holder.tvName.text = user.name
        holder.ivDelete.setOnClickListener{
            listener.deleteUser(user)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}