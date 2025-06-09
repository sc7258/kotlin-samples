package com.sc7258.appconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppConfigApplication

fun main(args: Array<String>) {
    runApplication<AppConfigApplication>(*args)
}
