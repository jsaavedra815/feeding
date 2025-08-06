package com.pucetec.feeding.repositories

import com.pucetec.feeding.models.responses.AnimalResponse
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class AnimalRestRepository(
    private val restTemplate: RestTemplate,
) {
    val baseUrl: String = "http://animal-service:8080/api/animals"

    fun findAnimalById(id: Long): AnimalResponse? {
        val url = "$baseUrl/$id"
        return restTemplate.getForObject(url, AnimalResponse::class.java)
    }
}