package com.example.travelplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.databinding.ActivityPlanBinding
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class PlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanBinding
    var startTime: String =""
    var endTime: String =""
    var plan: String =""
    val dayList = ArrayList<LocalDate>()
    val adapter = DayAdapter(dayList, startTime, endTime, plan)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        adapter.itemClicked.observe(this) {
            val intent = Intent(this, AddTimePlanActivity::class.java)
            startActivityForResult(intent, RESULT_OK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RESULT_OK && resultCode == RESULT_OK) {
            startTime = data?.extras?.getString("startTime").toString()
            endTime = data?.extras?.getString("endTime").toString()
            plan = data?.extras?.getString("plan").toString()

        }
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


}