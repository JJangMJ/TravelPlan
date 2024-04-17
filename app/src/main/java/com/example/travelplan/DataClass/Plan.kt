package com.example.travelplan.DataClass

import java.io.Serializable
import java.time.LocalDate
import java.util.Date

class Plan(
    var id: String,
    var destination: String,
    var startDate: LocalDate,
    var endDate: LocalDate) : Serializable {
}