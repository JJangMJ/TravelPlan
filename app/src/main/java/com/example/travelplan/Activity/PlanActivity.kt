package com.example.travelplan.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.Adapter.DayAdapter
import com.example.travelplan.Adapter.TimePlanAdapter
import com.example.travelplan.DataClass.Day
import com.example.travelplan.DataClass.Plan
import com.example.travelplan.DataClass.TimePlan
import com.example.travelplan.databinding.ActivityPlanBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class PlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlanBinding
    val dayList = ArrayList<Day>()
    val timePlanList = ArrayList<TimePlan>()
    lateinit var dayAdapter: DayAdapter
    lateinit var timePlanAdapter: TimePlanAdapter
    lateinit var plan: Plan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        plan = intent.getSerializableExtra("plan") as Plan
        initUI()
    }

    override fun onStart() {
        super.onStart()
        timePlanList.clear()
        fetchTimeList()
    }

    private fun initUI() {
        dayAdapter = DayAdapter(dayList, plan.id)
        timePlanAdapter = TimePlanAdapter(timePlanList)

        binding.destinationTv.setText(plan.destination)
        binding.departureDateTv.setText(plan.startDate.toString())
        binding.endDateTv.setText(plan.endDate.toString())

        val dayGap = ChronoUnit.DAYS.between(plan.startDate, plan.endDate)

        // TODO : 날짜만 추가 + TimePlan의 리스트는 일단 빈 배열로
        for (i in 0..dayGap) {
//            dayList.add(plan.startDate.plusDays(i))
        }

        binding.dayRv.adapter = dayAdapter
        binding.dayRv.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchTimeList() {
        // TODO : 존재하는 dayList에 서버에서 가지고 온 TimePlan의 리스트들을 추가

        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("TimePlan")
        collectionRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data["planId"] == plan.id){
                        val startTime = document.data["startTime"] as Timestamp
                        val endTime = document.data["endTime"] as Timestamp
                        val plan = document.data["title"] as String

                        timePlanList.add(TimePlan(document.id, startTime, endTime, plan))
                        Log.d("timeListTest","$timePlanList")
                    }
                }
                timePlanAdapter.timePlanList = timePlanList
                timePlanAdapter.notifyDataSetChanged()
            }
    }

}