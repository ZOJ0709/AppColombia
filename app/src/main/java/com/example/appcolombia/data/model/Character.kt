package com.example.appcolombia.data.model


data class CharacterResponse(
    val results: List<colombia>

)

data class colombia (
    val id: Int,
    val name: String
)