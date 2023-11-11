package com.example.travelplan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addPlanBtn.setOnClickListener {
            val intent = Intent(this, AddPlanActivity::class.java)
            startActivity(intent)
        }

        var planList = ArrayList<Plan>()
        planList.add(Plan("a", "일본", LocalDate.now(), LocalDate.now()))
        planList.add(Plan("b", "중국", LocalDate.now(), LocalDate.now()))
        planList.add(Plan("c", "대만", LocalDate.now(), LocalDate.now()))

        var adapter = PlanAdapter(planList)
        adapter.planList = planList

        binding.planListRv.adapter = adapter
        binding.planListRv.layoutManager = LinearLayoutManager(this)

        adapter.itemClicked.observe(this) {
            val intent = Intent(this, PlanActivity::class.java)
            intent.putExtra("plan", it)
            startActivity(intent)
        }
    }
}