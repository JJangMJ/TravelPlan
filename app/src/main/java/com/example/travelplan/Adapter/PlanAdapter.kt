package com.example.travelplan.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.DataClass.Plan
import com.example.travelplan.ViewHolder.PlanViewHolder
import com.example.travelplan.databinding.ItemPlanBinding

class PlanAdapter(var planList: ArrayList<Plan>) : RecyclerView.Adapter<PlanViewHolder>() {
    val itemClicked = MutableLiveData<Plan>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val binding = ItemPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanViewHolder(binding)
    }
    // 뷰홀더 객체가 새로 만들때마다 호출한다. 이 메서드는 뷰 레이아웃을 이용해 뷰 객체를 만든다.
    // 부 객체를 새로 만든 부홀더 객체에 담아 반환한다.

    override fun getItemCount(): Int {
        return planList.size
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = planList[position]
        holder.bindData(plan)
        holder.binding.parentLl.setOnClickListener {
            itemClicked.postValue(plan)
        }
    }
    // 뷰 홀더가 재사용될때 호출된다. 즉 아래로 스크롤해 기존것이 사라지고 재사용될때를 말하는 것이다.
    // 리사이클러뷰는 뷰 홀더를 데이터와 연결할 때 이 메서드를 호출한다.
    // 즉, 뷰 홀더 안에 데이터들인 뷰 객체들이 있는데 그걸 연결할때 호출해주는 것이다.
}