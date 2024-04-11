package com.example.travelplan

import java.io.Serializable

class Day(
    var id: String,
    var startTime: String,
    var endTime: String,
    var plan: String): Serializable {
}