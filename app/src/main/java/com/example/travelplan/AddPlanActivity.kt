package com.example.travelplan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.travelplan.databinding.ActivityAddPlanBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

class AddPlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPlanBinding

    var departureLocalDate: LocalDate? = null
    var endLocalDate: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        action()
    }

    fun action() {
        binding.departureDateBtn.setOnClickListener {
            showDialog("departureDateBtn")
        }

        binding.endDateBtn.setOnClickListener {
            showDialog("endDateBtn")
        }

        binding.completeBtn.setOnClickListener {
            val destination = binding.destinationInputTv.text.toString()

            if (destination.isEmpty()) {
                Toast.makeText(this, "목적지를 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (departureLocalDate == null) {
                Toast.makeText(this, "출발날짜를 고르세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (endLocalDate == null) {
                Toast.makeText(this, "도착날짜를 고르세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val departureDate = Date.from(departureLocalDate!!.atStartOfDay(ZoneId.systemDefault()).toInstant())
            val endDate = Date.from(endLocalDate!!.atStartOfDay(ZoneId.systemDefault()).toInstant())
            val db = Firebase.firestore
            val plan = hashMapOf(
                "destination" to destination,
                "startDate" to Timestamp(departureDate),
                "endDate" to Timestamp(endDate)
            )

            db.collection("Plan")
                .add(plan)
                .addOnSuccessListener { documentReference ->

                    finish()
                }
        }
    }

    fun showDialog(dateBtn: String) {
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            var dateString  = "${year}-${month+1}-${dayOfMonth}"
            if(month>10){
                dateString  = "${year}-${month+1}-${dayOfMonth}"
            }
            else {
                dateString  = "${year}-${0}${month+1}-${dayOfMonth}"
            }
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(dateString, formatter)

            if(dateBtn == "departureDateBtn") {
                binding.departureDateBtn.text = dateString
                departureLocalDate = date
            } else {
                binding.endDateBtn.text = dateString
                endLocalDate = date
            }
        }

        val cal = Calendar.getInstance()
        val dateDialog =
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))
        dateDialog.show()
    }
}

// real Final test
// one more test2
// one more test3
// tq
// create Jang branch
// create Jang2 branch
// idk