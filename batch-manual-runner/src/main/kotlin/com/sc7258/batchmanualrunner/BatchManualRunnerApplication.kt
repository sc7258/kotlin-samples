package com.sc7258.batchmanualrunner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BatchManualRunnerApplication

fun main(args: Array<String>) {
    runApplication<BatchManualRunnerApplication>(*args)
}
