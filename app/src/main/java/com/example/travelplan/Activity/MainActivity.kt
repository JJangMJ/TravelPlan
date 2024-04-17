package com.example.travelplan.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.Adapter.PlanAdapter
import com.example.travelplan.DataClass.Plan
import com.example.travelplan.databinding.ActivityMainBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.time.ZoneId

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val planList = ArrayList<Plan>()
    lateinit var adapter: PlanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        action()
    }

    override fun onStart() {
        super.onStart()
        planList.clear()
        fetchPlanList()
    }

    private fun initUI() {
        adapter = PlanAdapter(planList)
        binding.planListRv.adapter = adapter
        binding.planListRv.layoutManager = LinearLayoutManager(this)
    }

    private fun action() {
        binding.addPlanBtn.setOnClickListener {
            val intent = Intent(this, AddPlanActivity::class.java)
            startActivity(intent)
        }

        adapter.itemClicked.observe(this) {
            val intent = Intent(this, PlanActivity::class.java)
            intent.putExtra("plan", it)
            startActivity(intent)
        }
    }
    private fun fetchPlanList() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Plan")

        collectionRef
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    val destination = document.data["destination"] as String
                    val startDateTimestamp = document.data["startDate"] as Timestamp
                    val startLocalDate = startDateTimestamp.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    val endDateTimestamp = document.data["endDate"] as Timestamp
                    val endLocalDate = endDateTimestamp.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

                    planList.add(Plan(document.id, destination, startLocalDate, endLocalDate))
                }
                adapter.planList = planList
                adapter.notifyDataSetChanged()
            }
    }
}