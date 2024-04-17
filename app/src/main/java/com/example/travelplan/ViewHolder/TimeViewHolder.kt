package com.example.travelplan.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.DataClass.Time
import com.example.travelplan.databinding.ItemTimeBinding

class TimeViewHolder(val binding: ItemTimeBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindData(time: Time) {
        binding.startTimeTv.text = time.startTime.toString()
        binding.endTimeTv.text = time.endTime.toString()
        binding.planTv.text = time.plan
    }
}