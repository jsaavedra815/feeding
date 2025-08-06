package com.pucetec.feeding.models.responses

data class AnimalResponse (
    val id: Long,
    val name: String,
    val species: String,
    val alimentation: String,
    val age: Int,
)