package com.example.mymaster.presentations.ServicesList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.ServicesModel

import com.example.mymaster.R

 class ServicesAdapter(private val items: List<ServicesModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_service, parent, false)
        ) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //val num = holder.itemView.findViewById<TextView>(R.id.ser_num)
        val name = holder.itemView.findViewById<EditText>(R.id.ser_name)
        val price = holder.itemView.findViewById<EditText>(R.id.ser_price)
        val time = holder.itemView.findViewById<EditText>(R.id.ser_time)
       // num.text = (position + 1).toString()
        name.setText(items[position].name)
        price.setText(items[position].price)
        time.setText(items[position].timeInWork)
        FragmentServicesList.names.add(name)
        FragmentServicesList.prices.add(price)
        FragmentServicesList.times.add(time)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}