package com.example.travelplan.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.DataClass.TimePlan
import com.example.travelplan.ViewHolder.TimePlanViewHolder
import com.example.travelplan.databinding.ItemTimeBinding

class TimePlanAdapter(var timePlanList: ArrayList<TimePlan>) : RecyclerView.Adapter<TimePlanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimePlanViewHolder {
        val binding = ItemTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimePlanViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return timePlanList.size
    }

    override fun onBindViewHolder(holder: TimePlanViewHolder, position: Int) {
        val time = timePlanList[position]
        holder.bindData(time)
    }
}