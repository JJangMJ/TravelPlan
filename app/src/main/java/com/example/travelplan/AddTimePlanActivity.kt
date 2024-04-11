package com.example.travelplan

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelplan.databinding.ActivityAddTimePlanBinding
import java.util.Calendar

class AddTimePlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTimePlanBinding
    private var plan = ""
    private var startTime = ""
    private var endTime = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTimePlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        action()
    }
    private fun action() {
        binding.setStartTimeBtn.setOnClickListener {
            binding.timePicker.setOnTimeChangedListener { context, hourOfDay, minute ->
                binding.setStartTimeBtn.text = "시작 시간 : ${hourOfDay}시 ${minute}분"
                startTime = binding.setStartTimeBtn.text.toString()
            }
        }
        binding.setEndTimeBtn.setOnClickListener {
            binding.timePicker.setOnTimeChangedListener { context, hourOfDay, minute ->
                binding.setEndTimeBtn.text = "종료 시간 : ${hourOfDay}시 ${minute}분"
                endTime = binding.setEndTimeBtn.text.toString()
            }
        }

        binding.completeBtn.setOnClickListener {
            if (binding.setStartTimeBtn.text != "시작 시간 설정" && binding.setEndTimeBtn.text != "종료 시간 설정") {
                plan = binding.writePlanTv.text.toString()
                setResult(RESULT_OK)
                intent.putExtra("startTime",startTime)
                intent.putExtra("endTime",endTime)
                intent.putExtra("plan",plan)
                finish()
            }
            else {
                Toast.makeText(this,"시작 시간 또는 종료 시간을 먼저 설정해주세요!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}