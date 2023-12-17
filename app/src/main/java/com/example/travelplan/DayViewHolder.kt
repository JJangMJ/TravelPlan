package com.example.travelplan

import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.databinding.ItemDayBinding
import com.example.travelplan.databinding.ItemPlanBinding
import java.time.LocalDate

class DayViewHolder(val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(date: LocalDate) {
        binding.dayTv.text = date.toString()
    }
}