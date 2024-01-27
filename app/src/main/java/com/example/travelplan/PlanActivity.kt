package com.example.travelplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.databinding.ActivityPlanBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class PlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        action()
    }

    fun initUI() {
        val plan = intent.getSerializableExtra("plan") as Plan
        binding.destinationTv.setText(plan.destination)
        binding.departureDateTv.setText(plan.startDate.toString())
        binding.endDateTv.setText(plan.endDate.toString())

        val dayGap = ChronoUnit.DAYS.between(plan.startDate, plan.endDate)
        val dayList = ArrayList<LocalDate>()

        for (i in 0..dayGap) {
            dayList.add(plan.startDate.plusDays(i))
        }

        // dayList -> Recycler
        val adapter = DayAdapter(dayList)

        binding.dayRv.adapter = adapter
        binding.dayRv.layoutManager = LinearLayoutManager(this)
    }

    fun action() {
        binding.deletePlanBtn.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            db.collection("Plan")
        }
    }
}