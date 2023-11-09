package com.example.travelplan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.databinding.ItemPlanBinding

class PlanAdapter(var planList: ArrayList<Plan>) : RecyclerView.Adapter<PlanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val binding = ItemPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return planList.size
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = planList[position]
        holder.bindData(plan)
    }
}