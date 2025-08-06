package com.pucetec.feeding.controllers

import com.pucetec.feeding.models.responses.FeedingResponse
import com.pucetec.feeding.services.FeedingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/feeding")
class FeedingController(
    private val service: FeedingService
) {
    @GetMapping("{id}")
    fun recommendFood(@PathVariable id: Long): FeedingResponse =
        service.recommendFood(id)
}