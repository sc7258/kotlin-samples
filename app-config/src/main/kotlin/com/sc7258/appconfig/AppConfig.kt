package com.sc7258.appconfig

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app")
class AppConfig(
    var name: String = "",
    var timeout: Int = 0,
) {
}