package com.example.travelplan

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelplan.databinding.ActivityMainBinding
import java.time.LocalDate
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.getField
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.ZoneId
import java.util.Date
import kotlin.time.Duration.Companion.days

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var documentList: ArrayList<Plan>? = null
    var documentID :String = ""
    lateinit var departureDate :Timestamp
    val db = FirebaseFirestore.getInstance()

    val docRef1 : CollectionReference = db.collection("Plan")
    val docRef2 = db.collection("Plan").document(docRef1.id)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addPlanBtn.setOnClickListener {
            val intent = Intent(this, AddPlanActivity::class.java)
            startActivity(intent)
        }

//        docRef1.get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                    Log.d(TAG, "${document.data.values} @@@@")
//                    documentList.add(document.data.values)
//
//                    documentID = document.id
//                    departureDate = documentList!!.elementAt(2)
//                    Log.d(TAG, "${documentList!!.elementAt(1)}------")
//                    Log.d(TAG, "${documentList!!.elementAt(2)}------")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }
//
////        val departureDate = documentList!!.elementAt(2)
////        val endDate = documentList!!.elementAt(3)
//
////        var departureLocalDate = departureDate.
////        var endDateLocalDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(endDate as Long), ZoneId.systemDefault())

        val planList = ArrayList<Plan>()
//        planList.add(Plan(documentID, documentList!!.elementAt(1).toString(),departureLocalDate, documentList[0]))
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
