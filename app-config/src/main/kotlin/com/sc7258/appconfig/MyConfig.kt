package com.sc7258.appconfig

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import kotlin.math.log

@Component
class MyConfig(
    @Value("\${app.name}") val appName: String,
    @Value("\${app.timeout}") val appTimeout: Int,
) {
    private val logger = KotlinLogging.logger {}

    fun showConfig(){
        logger.info("MyConfig - App Name: $appName, Timeout: $appTimeout")
    }
}