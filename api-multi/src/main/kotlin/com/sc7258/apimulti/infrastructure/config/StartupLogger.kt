package com.sc7258.apimulti.infrastructure.config

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory

@Component
class StartupLogger : ApplicationListener<ApplicationReadyEvent> {

    companion object {
        private val log = LoggerFactory.getLogger(StartupLogger::class.java)
    }

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        log.info(">>> 애플리케이션이 완전히 시작되었습니다!")
    }
}