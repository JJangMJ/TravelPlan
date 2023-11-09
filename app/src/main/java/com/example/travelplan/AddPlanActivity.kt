package com.example.travelplan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.travelplan.databinding.ActivityAddPlanBinding
import java.util.Calendar
import java.util.Date

class AddPlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPlanBinding

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
            val departureDate = binding.destinationInputTv.text.toString()
            val endDate = binding.endDateBtn.text.toString()

            val plan = Plan("", destination, departureDate, endDate)

            Log.d("@@@", destination)
            Log.d("@@@", departureDate)
            Log.d("@@@", endDate)
            finish()
        }
    }

    fun showDialog(dateBtn: String) {
        val cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            var dateString = ""
            dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"

            // year, month, dayOfMonth 를 가지고 Date 자료형을 만들 줄 모른다.
            // 당연히 모른다. Date 자료형을 처음 써보기 때문.
            // 구글링

            val departureDate : Date = cal.time
            val endDate : Date = cal.time

            if(dateBtn == "departureDateBtn") {
                binding.departureDateBtn.text = dateString

            } else {
                binding.endDateBtn.text = dateString
            }
        }

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