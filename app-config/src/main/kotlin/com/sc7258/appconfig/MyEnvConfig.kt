package com.sc7258.appconfig

import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class MyEnvConfig(
    val env: Environment
) {
    private val logger = mu.KotlinLogging.logger {}

    fun showConfig(){
        val appName = env.getProperty("app.name")
        val timeout = env.getProperty("app.timeout", Int::class.java)
        logger.info("MyEnvConfig - App Name: $appName, Timeout: $timeout")
    }
}