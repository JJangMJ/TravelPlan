package com.example.travelplan.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.Activity.AddDayPlanActivity
import com.example.travelplan.ViewHolder.DayViewHolder
import com.example.travelplan.databinding.ItemDayBinding
import java.time.LocalDate

class DayAdapter(var dayList: ArrayList<LocalDate>) : RecyclerView.Adapter<DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = dayList[position]
        holder.bindData(day) // 이 곳에서 바인딩 해줘도 되는데 그냥 역할을 제대로 나누고 싶어서 DayViewHolder 클래스에 bindData()함수를 만들어 그곳에서 바인딩해줌.
        holder.binding.addPlanBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, AddDayPlanActivity::class.java)
            intent.putExtra("day", day)
            holder.itemView.context.startActivity(intent)
        }
//        holder.binding.timePlanRv.
    }

}