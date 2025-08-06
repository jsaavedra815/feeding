package com.pucetec.feeding.services

import com.pucetec.feeding.models.responses.FeedingResponse
import com.pucetec.feeding.repositories.AnimalRestRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class FeedingService(
    private val animalRepository: AnimalRestRepository
) {
    fun recommendFood(animalId: Long): FeedingResponse {
        val animal = animalRepository.findAnimalById(animalId)
        return FeedingResponse(
            preferredFood = getFoodForAlimentation(animal?.alimentation),
            animal = animal
        )
    }

    fun getFoodForAlimentation(alimentation: String?): String {
        return when (alimentation?.uppercase()) {
            "CARNIVORO" -> "Carne"
            "OMNIVORO" -> "Carne y vegetales"
            "HERBIVORO" -> "Hierba y plantas"
            "FRUGIVORO" -> "Frutas"
            "GRANIVORO" -> "Semillas y granos"
            else -> "Comida desconocida"
        }
    }
}