package com.example.mymaster.presentations.ServicesList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.ServicesModel
import com.example.mymaster.R

class ServicesAdapter(private val items: MutableList<ServicesModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        ) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //val num = holder.itemView.findViewById<TextView>(R.id.ser_num)
        val name = holder.itemView.findViewById<EditText>(R.id.ser_name)
        val price = holder.itemView.findViewById<EditText>(R.id.ser_price)
        val time = holder.itemView.findViewById<EditText>(R.id.ser_time)
        val info = holder.itemView.findViewById<EditText>(R.id.ser_info)
        val btn = holder.itemView.findViewById<ImageButton>(R.id.btn_delete_service)
        // num.text = (position + 1).toString()
        FragmentServicesList.btnList.add(btn)
        name.setText(items[position].name)
        price.setText(items[position].price)
        time.setText(items[position].timeInWork)
        info.setText(items[position].info)

        btn.setOnClickListener {
            items[position].status = false
            FragmentServicesList.statusList[position] = false
            FragmentServicesList.itemsTemp[position].status = false
            items.removeAt(position)
            this.notifyItemRemoved(position)
            notifyItemRangeChanged(position, 1)
        }



        FragmentServicesList.names.add(name)
        FragmentServicesList.prices.add(price)
        FragmentServicesList.times.add(time)
        FragmentServicesList.infoList.add(info)

        FragmentServicesList.statusList.add(items[position].status!!)
    }

    override fun getItemCount(): Int {
        return items.size
    }
/*
    private fun removeItem(position: Int) {

    }
*/
}