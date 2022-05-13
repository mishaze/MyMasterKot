package com.example.mymaster.presentations.scheduleActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Domain.models.RecordingSessionModel
import com.example.mymaster.R

class ScheduleAdapter(private val items: List<RecordingSessionModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.schedule_lisr_item, parent, false)
        ) {}
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val name = holder.itemView.findViewById<TextView>(R.id.sch_item_name)
        val service = holder.itemView.findViewById<TextView>(R.id.sch_item_services)
        val timeStart =
            holder.itemView.findViewById<TextView>(R.id.sch_item_time_start)
        val timeEnd = holder.itemView.findViewById<TextView>(R.id.sch_item_time_end)
        val date = holder.itemView.findViewById<TextView>(R.id.sch_item_date)

        name.text = items[position].uid
        service.text = items[position].uids
        timeStart.text = items[position].time.toString()
        timeEnd.text = items[position].time.toString()
        date.text = items[position].date
    }

    override fun getItemCount(): Int {
        return items.size
    }
    private fun timeUnParse(tempstr: String): String {
        val temp = tempstr.toInt()
        var minute = 0
        var hours = 0
        hours = temp / 60
        minute = temp - hours * 60
        val sminute: String = if (minute == 0) {
            "00"
        } else if (minute < 10) {
            "0$minute"
        } else {
            minute.toString()
        }
        val shours: String = if (hours == 0) {
            "00"
        } else if (hours < 10) {
            "0$hours"
        } else {
            hours.toString()
        }
        return "$shours:$sminute"
    }


}