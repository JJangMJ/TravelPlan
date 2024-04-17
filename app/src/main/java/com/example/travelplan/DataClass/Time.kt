package com.example.travelplan.DataClass

import com.google.firebase.Timestamp
import java.io.Serializable
import java.time.LocalDate

class Time(
    var id: String,
    var startTime: Timestamp,
    var endTime: Timestamp,
    var plan: String ) : Serializable {
}