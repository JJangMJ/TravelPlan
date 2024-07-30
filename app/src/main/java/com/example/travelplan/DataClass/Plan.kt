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
// 데이터 클래스로, MainActivity의 리사이클러뷰의 리스트에 각 아이템의 내용이 담길 데이터 클래스를 하나 만들었다.