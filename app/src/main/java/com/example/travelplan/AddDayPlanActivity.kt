package com.example.travelplan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.travelplan.databinding.ActivityAddDayPlanBinding

class AddDayPlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDayPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDayPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}