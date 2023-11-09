package com.example.travelplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.databinding.ActivityMainBinding
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
        planList.add(Plan("a", "일본", Date(), Date()))
        planList.add(Plan("b", "일본", Date(), Date()))
        planList.add(Plan("c", "일본", Date(), Date()))

        var adapter = PlanAdapter(planList)

        binding.planListRv.adapter = adapter
        binding.planListRv.layoutManager = LinearLayoutManager(this)
    }
}