package com.sc7258.apiversioning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication
@ComponentScan(
    basePackages = ["com.sc7258.apiversioning"],
    excludeFilters = [
        ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = [
                com.sc7258.apiversioning.generated.v1.api.DefaultExceptionHandler::class,
                com.sc7258.apiversioning.generated.v2.api.DefaultExceptionHandler::class
            ]
        )
    ]
)
class ApiversioningApplication

fun main(args: Array<String>) {
    runApplication<ApiversioningApplication>(*args)
}
