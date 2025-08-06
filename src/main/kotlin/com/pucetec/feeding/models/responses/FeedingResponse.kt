package com.pucetec.feeding.models.responses

import com.fasterxml.jackson.annotation.JsonProperty

data class FeedingResponse(
    @JsonProperty("preferred_food")
    val preferredFood: String = "",
    val animal: AnimalResponse? = null,
)