package com.example.travelplan.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.Adapter.DayAdapter
import com.example.travelplan.Adapter.TimeAdapter
import com.example.travelplan.DataClass.Plan
import com.example.travelplan.DataClass.Time
import com.example.travelplan.databinding.ActivityPlanBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class PlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanBinding
    val dayList = ArrayList<LocalDate>()
    val timeList = ArrayList<Time>()
    lateinit var dayAdapter: DayAdapter
    lateinit var timeAdapter: TimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchTimeList()
        initUI()
    }

    override fun onStart() {
        super.onStart()
        timeList.clear()
        fetchTimeList()
    }

    private fun initUI() {
        dayAdapter = DayAdapter(dayList)
        timeAdapter = TimeAdapter(timeList)

        val plan = intent.getSerializableExtra("plan") as Plan

        binding.destinationTv.setText(plan.destination)
        binding.departureDateTv.setText(plan.startDate.toString())
        binding.endDateTv.setText(plan.endDate.toString())

        val dayGap = ChronoUnit.DAYS.between(plan.startDate, plan.endDate)

        for (i in 0..dayGap) {
            dayList.add(plan.startDate.plusDays(i))
        }

        binding.dayRv.adapter = dayAdapter
        binding.dayRv.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchTimeList() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("DayPlan")
        collectionRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val startTime = document.data["startTime"] as Timestamp
                    val endTime = document.data["endTime"] as Timestamp
                    val plan = document.data["title"] as String

                    timeList.add(Time(document.id, startTime, endTime, plan))
                }
                timeAdapter.timeList = timeList
                timeAdapter.notifyDataSetChanged()
            }
    }

}