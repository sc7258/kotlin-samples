package com.sc7258.apimulti.controller.api.v1.intellian

import com.sc7258.apimulti.api.intellian.IntellianApiDelegate
import com.sc7258.apimulti.model.intellian.IntellianDevice
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class IntellianApiDelegateImpl : IntellianApiDelegate {

    companion object {
        private val log = LoggerFactory.getLogger(IntellianApiDelegateImpl::class.java)
    }

    override fun getIntellianDevices(): ResponseEntity<List<IntellianDevice>> {
        log.info("Fetching all Intellian devices Start")

        // TODO: 실제 장비 목록 조회 로직 구현
        val devices = listOf(
            IntellianDevice(
                serialNumber = "INTL-001",
                modelName = "v100",
                firmwareVersion = "1.23",
                isActive = true
            ),
            IntellianDevice(
                serialNumber = "INTL-002",
                modelName = "c700",
                firmwareVersion = "2.01",
                isActive = false
            )
        )

        log.info("Fetching all Intellian devices Done")
        return ResponseEntity.ok(devices)
    }
}