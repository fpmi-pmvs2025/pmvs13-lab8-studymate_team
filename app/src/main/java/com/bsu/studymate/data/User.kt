package com.bsu.studymate.data

data class User(
    val id: Long = 0,
    val email: String,
    val pass: String,
    val firstName: String,
    val lastName: String,
    val groupNo: Int
)