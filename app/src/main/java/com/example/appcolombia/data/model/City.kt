package com.example.appcolombia.data.model

data class City(
    val id: Int,
    val name: String,
    val description: String?,
    val surface: Int?,
    val population: Int?,
    val postalCode: String?,
    val departmentId: Int?
)