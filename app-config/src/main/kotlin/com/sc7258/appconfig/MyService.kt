package com.sc7258.appconfig

import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class MyService(
    val appConfig: AppConfig
) {
    private val logger = KotlinLogging.logger {}

    fun showConfig(){
        logger.info("MyService - App Name: ${appConfig.name}, Timeout: ${appConfig.timeout}")
    }
}