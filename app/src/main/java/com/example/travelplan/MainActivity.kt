package com.example.travelplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addPlanBtn.setOnClickListener {
            val intent = Intent(this, SearchDestinationActivity::class.java)
            startActivity(intent)
        }

        val planList = ArrayList<Plan>()
        planList.add(Plan("a", "일본", LocalDate.now(), LocalDate.now().plusDays(10)))
        planList.add(Plan("b", "중국", LocalDate.now(), LocalDate.now().plusDays(5)))
        planList.add(Plan("c", "대만", LocalDate.now(), LocalDate.now().plusDays(4)))

        val adapter = PlanAdapter(planList)
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