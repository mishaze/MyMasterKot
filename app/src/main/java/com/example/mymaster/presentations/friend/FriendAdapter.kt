package com.example.mymaster.presentations.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymaster.R

class FriendAdapter(private val items: MutableList<com.example.domain.Domain.models.ClientInform>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.friend_item, parent, false)
        ) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val first_name = holder.itemView.findViewById<TextView>(R.id.fri_item_first_name)
        first_name.text = items[position].name
        val second_name = holder.itemView.findViewById<TextView>(R.id.fri_item_second_name)
        second_name.text = items[position].surname
        val phone = holder.itemView.findViewById<TextView>(R.id.fri_item_phone)
        phone.text = items[position].phone_number
        val email = holder.itemView.findViewById<TextView>(R.id.fri_item_email)
        email.text = items[position].email
    }

    override fun getItemCount(): Int {
        return items.size
    }

}