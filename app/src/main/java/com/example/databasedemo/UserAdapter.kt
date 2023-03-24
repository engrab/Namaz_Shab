package com.example.databasedemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.databasedemo.database.User

class UserAdapter(val context: Context, val list: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvIndex: TextView = view.findViewById(R.id.tvIndex)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = list.get(position)
        holder.tvName.text = user.name

        var indexOf = list.indexOf(user)
        indexOf++

        holder.tvIndex.text = indexOf.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}