package com.sc7258.apiversioning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiVersioningApplication

fun main(args: Array<String>) {
    runApplication<ApiVersioningApplication>(*args)
}
