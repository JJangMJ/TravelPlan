package com.example.travelplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.travelplan.databinding.ActivityPlanBinding

class PlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plan = intent.getSerializableExtra("plan") as Plan
        binding.destinationTv.setText(plan.destination)
        binding.departureDateTv.setText(plan.startDate.toString())
        binding.endDateTv.setText(plan.endDate.toString())
        Log.d("$$$", plan!!.toString())
    }
}