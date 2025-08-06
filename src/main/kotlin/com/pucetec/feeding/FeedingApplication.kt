package com.pucetec.feeding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeedingApplication

fun main(args: Array<String>) {
	runApplication<FeedingApplication>(*args)
}
