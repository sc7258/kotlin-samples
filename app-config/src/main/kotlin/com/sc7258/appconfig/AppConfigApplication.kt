package com.sc7258.appconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppConfigApplication

fun main(args: Array<String>) {
    val context = runApplication<AppConfigApplication>(*args)

    val myConfig = context.getBean(MyConfig::class.java)
    myConfig.showConfig()

    val myEnvConfig = context.getBean(MyEnvConfig::class.java)
    myEnvConfig.showConfig()
    
    val myService = context.getBean(MyService::class.java)
    myService.showConfig()

}
