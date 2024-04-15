package com.example.travelplan

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplan.databinding.ItemDayBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.time.LocalDate
import java.time.LocalDateTime

class DayViewHolder(val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {
    val db = FirebaseFirestore.getInstance()
    val collectionRef = db.collection("DayPlan")
    fun bindData(date: LocalDate) {
        binding.dayTv.text = date.toString()
    }
    fun showDayPlan() {
        collectionRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("testStartime","${document.data["startTime"]}")
                    if (binding.dayTv.text == document.data["startTime"]) {

                    }
                }
            }
    }
}