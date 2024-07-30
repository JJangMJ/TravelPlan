package com.example.travelplan.ViewHolder

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.DataClass.Day
import com.example.travelplan.databinding.ItemDayBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.time.LocalDate
import java.time.LocalDateTime

class DayViewHolder(val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(day: Day) {
        binding.dayTv.text = day.date.toString()
    }
}