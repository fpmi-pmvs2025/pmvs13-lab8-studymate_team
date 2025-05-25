package com.bsu.studymate.data

data class Lesson(
    val id: Long = 0,
    val day: String,
    val groupNo: Int,
    val subject: String,
    val time: String,
    val room: String
)