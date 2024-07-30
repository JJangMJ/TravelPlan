package com.example.travelplan.DataClass

import java.io.Serializable
import java.time.LocalDate

class Day(
    var date: LocalDate,
    var timePlanList: ArrayList<TimePlan>) {
}

// 데이터 클래스로 item 에 들어갈 데이터들의 클래스이다. 여기는 PlanActivity의