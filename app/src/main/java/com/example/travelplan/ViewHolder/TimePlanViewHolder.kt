package com.example.travelplan.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.DataClass.TimePlan
import com.example.travelplan.databinding.ItemTimeBinding

class TimePlanViewHolder(val binding: ItemTimeBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindData(timePlan: TimePlan) {
        binding.startTimeTv.text = timePlan.startTime.toString()
        binding.endTimeTv.text = timePlan.endTime.toString()
        binding.planTv.text = timePlan.plan
    }
}