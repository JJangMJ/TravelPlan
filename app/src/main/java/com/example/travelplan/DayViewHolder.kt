package com.example.travelplan

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.databinding.ItemDayBinding
import java.time.LocalDate

class DayViewHolder(val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(date: LocalDate, startTime: String, endTime: String, plan: String) {
        binding.dayTv.text = date.toString()
        binding.startTimeTv.text = startTime
        binding.endTimeTv.text = endTime
        binding.planTv.text = plan
    }
}