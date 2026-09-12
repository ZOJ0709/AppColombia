package com.example.appcolombia.data.model

data class Department(
    val id: Int,
    val name: String,
    val description: String?,
    val cityCapitalId: Int?,
    val municipalities: Int?,
    val surface: Int?,
    val population: Int?,
    val phonePrefix: String?,
    val regionId: Int?
)