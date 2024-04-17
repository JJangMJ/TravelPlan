package com.example.travelplan.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.DataClass.Time
import com.example.travelplan.ViewHolder.TimeViewHolder
import com.example.travelplan.databinding.ItemTimeBinding

class TimeAdapter(var timeList: ArrayList<Time>) : RecyclerView.Adapter<TimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val binding = ItemTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return timeList.size
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        val time = timeList[position]
        holder.bindData(time)
    }
}