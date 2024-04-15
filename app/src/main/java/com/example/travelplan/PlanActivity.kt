package com.example.travelplan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.databinding.ActivityPlanBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class PlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanBinding
    val dayList = ArrayList<LocalDate>()
    val adapter = DayAdapter(dayList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }
    private fun initUI() {
        val plan = intent.getSerializableExtra("plan") as Plan
        binding.destinationTv.setText(plan.destination)
        binding.departureDateTv.setText(plan.startDate.toString())
        binding.endDateTv.setText(plan.endDate.toString())

        val dayGap = ChronoUnit.DAYS.between(plan.startDate, plan.endDate)

        for (i in 0..dayGap) {
            dayList.add(plan.startDate.plusDays(i))
        }
        binding.dayRv.adapter = adapter
        binding.dayRv.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()

    }

}