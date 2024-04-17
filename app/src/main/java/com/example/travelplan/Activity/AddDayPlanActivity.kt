package com.example.travelplan.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelplan.databinding.ActivityAddDayPlanBinding
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.firestore
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AddDayPlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDayPlanBinding
    private lateinit var day: LocalDate
    lateinit var startDateTimestamp: Timestamp
    lateinit var endDateTimestamp: Timestamp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDayPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        action()
        day = intent.getSerializableExtra("day") as LocalDate
    }
    private fun action() {
        binding.setStartTimeBtn.setOnClickListener {
            binding.timePicker.setOnTimeChangedListener { context, hourOfDay, minute ->
                binding.setStartTimeBtn.text = "시작 시간: ${hourOfDay}시 ${minute}분"
                val hourString = hourOfDay.toString().padStart(2, '0')
                val minuteString = minute.toString().padStart(2, '0')
                var dateString = "${day.year}-${day.month.value}-${day.dayOfMonth} $hourString:$minuteString"
                if(day.month.value>10 && day.dayOfMonth>=10){
                    dateString  = "${day.year}-${day.month.value}-${day.dayOfMonth} $hourString:$minuteString"
                }
                else if (day.month.value<10 && day.dayOfMonth>=10) {
                    dateString  = "${day.year}-${0}${day.month.value}-${day.dayOfMonth} $hourString:$minuteString"
                }
                else if (day.month.value>10 && day.dayOfMonth<10) {
                    dateString  = "${day.year}-$${day.month.value}-${0}${day.dayOfMonth} $hourString:$minuteString"
                }
                else if (day.month.value<10 && day.dayOfMonth<10) {
                    dateString  = "${day.year}-${0}${day.month.value}-${0}${day.dayOfMonth} $hourString:$minuteString"
                }
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val startDate = LocalDateTime.parse(dateString, formatter)
                val instant = startDate.atZone(ZoneId.systemDefault()).toInstant()
                startDateTimestamp = Timestamp(instant.epochSecond, instant.nano)
            }
        }
        binding.setEndTimeBtn.setOnClickListener {
            binding.timePicker.setOnTimeChangedListener { context, hourOfDay, minute ->
                binding.setEndTimeBtn.text = "종료 시간: ${hourOfDay}시 ${minute}분"
                val hourString = hourOfDay.toString().padStart(2, '0')
                val minuteString = minute.toString().padStart(2, '0')
                var dateString = "${day.year} ${day.month.value} ${day.dayOfMonth} $hourString $minuteString"
                if(day.month.value>10 && day.dayOfMonth>=10){
                    dateString  = "${day.year}-${day.month.value}-${day.dayOfMonth} $hourString:$minuteString"
                }
                else if (day.month.value<10 && day.dayOfMonth>=10) {
                    dateString  = "${day.year}-${0}${day.month.value}-${day.dayOfMonth} $hourString:$minuteString"
                }
                else if (day.month.value>10 && day.dayOfMonth<10) {
                    dateString  = "${day.year}-$${day.month.value}-${0}${day.dayOfMonth} $hourString:$minuteString"
                }
                else if (day.month.value<10 && day.dayOfMonth<10) {
                    dateString  = "${day.year}-${0}${day.month.value}-${0}${day.dayOfMonth} $hourString:$minuteString"
                }
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val endDate = LocalDateTime.parse(dateString, formatter)
                val instant = endDate.atZone(ZoneId.systemDefault()).toInstant()
                endDateTimestamp = Timestamp(instant.epochSecond, instant.nano)
            }
        }

        binding.completeBtn.setOnClickListener {
            if (binding.setStartTimeBtn.text != "시작 시간 설정" && binding.setEndTimeBtn.text != "종료 시간 설정") {
                var title = binding.writePlanTv.text.toString()
                var db = Firebase.firestore
                var dayPlan = hashMapOf(
                    "startTime" to startDateTimestamp,
                    "endTime" to endDateTimestamp,
                    "title" to title
                )

                db.collection("DayPlan")
                    .add(dayPlan)
                    .addOnSuccessListener { documentReference ->
                        finish()
                    }
            }
            else {
                Toast.makeText(this,"시작 시간 또는 종료 시간을 먼저 설정해주세요!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}