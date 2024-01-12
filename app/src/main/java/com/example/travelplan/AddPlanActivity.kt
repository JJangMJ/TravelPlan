package com.example.travelplan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.travelplan.databinding.ActivityAddPlanBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

class AddPlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPlanBinding

    var departureDate: LocalDate? = null
    var endDate: LocalDate? = null

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
            else if (departureDate == null) {
                Toast.makeText(this, "출발날짜를 고르세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (endDate == null) {
                Toast.makeText(this, "도착날짜를 고르세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val plan = Plan("a", destination, departureDate!!, endDate!!)
            Log.d("@@@", destination!!.toString())
            Log.d("@@@", departureDate!!.toString())
            Log.d("@@@", endDate!!.toString())

            finish()
        }
    }

    fun showDialog(dateBtn: String) {
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val dateString  = "${year}-${month+1}-${dayOfMonth}"
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(dateString, formatter)

            if(dateBtn == "departureDateBtn") {
                binding.departureDateBtn.text = dateString
                departureDate = date
            } else {
                binding.endDateBtn.text = dateString
                endDate = date
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