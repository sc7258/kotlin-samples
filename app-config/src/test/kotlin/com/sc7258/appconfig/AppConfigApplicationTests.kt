package com.sc7258.appconfig

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals

@SpringBootTest
class AppConfigApplicationTests {

    @Test
    fun contextLoads() {
    }

}

@SpringBootTest
@ActiveProfiles("dev")
class DevProfileConfigTests {

    @Autowired
    lateinit var appConfig: AppConfig

    @Test
    fun `should load dev profile configuration`() {
        assertEquals("MyKotlinApp-Dev", appConfig.name)
        assertEquals(60, appConfig.timeout)
    }
}

@SpringBootTest
@ActiveProfiles("prod")
class ProdProfileConfigTests {

    @Autowired
    lateinit var appConfig: AppConfig

    @Test
    fun `should load prod profile configuration`() {
        assertEquals("MyKotlinApp-Prod", appConfig.name)
        assertEquals(120, appConfig.timeout)
    }
}
