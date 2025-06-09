package com.sc7258.applogger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppLoggerApplication

fun main(args: Array<String>) {
    runApplication<AppLoggerApplication>(*args)
}
