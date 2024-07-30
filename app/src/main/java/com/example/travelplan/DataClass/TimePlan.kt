package com.example.travelplan.DataClass

import com.google.firebase.Timestamp
import java.io.Serializable

class TimePlan(
    var id: String,
    var startTime: Timestamp,
    var endTime: Timestamp,
    var plan: String ) : Serializable {
}