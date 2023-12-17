package com.example.travelplan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.databinding.ItemDayBinding
import java.time.LocalDate

class DayAdapter(var dayList: ArrayList<LocalDate>) : RecyclerView.Adapter<DayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false) //XML을 객체화 함
        return DayViewHolder(binding) // 객체화된 XML(binding)과 ViewHolder를 연결해서 생성된 ViewHolder 반환
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = dayList[position]
        holder.bindData(day)
    }
}