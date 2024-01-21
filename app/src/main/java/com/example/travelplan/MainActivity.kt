package com.example.travelplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.databinding.ActivityMainBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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

            val db = Firebase.firestore
            val plan = hashMapOf(
                "destination" to "japan",
                "startDate" to Timestamp.now(),
                "endDate" to Timestamp.now()
            )

            db.collection("Plan")
                .add(plan)
                .addOnSuccessListener { documentReference ->
                    // ToDo
                }
        }

        val planList = ArrayList<Plan>()
        planList.add(Plan("a", "일본", LocalDate.now(), LocalDate.now().plusDays(10)))
        planList.add(Plan("b", "중국", LocalDate.now(), LocalDate.now().plusDays(5)))
        planList.add(Plan("c", "대만", LocalDate.now(), LocalDate.now().plusDays(4)))

        val adapter = PlanAdapter(planList)
        binding.planListRv.adapter = adapter
        binding.planListRv.layoutManager = LinearLayoutManager(this)

        adapter.itemClicked.observe(this) {
            val intent = Intent(this, PlanActivity::class.java)
            intent.putExtra("plan", it)
            startActivity(intent)
        }
    }
}

//test test
// just test 1

