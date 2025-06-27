package com.sc7258.apimulti.controller.api.v1.lband

import com.sc7258.apimulti.api.lband.LbandApiDelegate
import com.sc7258.apimulti.controller.api.v1.intellian.IntellianApiDelegateImpl
import com.sc7258.apimulti.model.lband.LbandApiResponse
import com.sc7258.apimulti.model.lband.LbandStatusResponse
import com.sc7258.apimulti.model.lband.LbandStatusResponseDataUsage
import com.sc7258.apimulti.model.lband.ServiceActivationRequest
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.UUID

@Service
class LbandApiDelegateImpl : LbandApiDelegate {

    companion object {
        private val log = LoggerFactory.getLogger(IntellianApiDelegateImpl::class.java)
    }

    override fun getLbandStatusByTerminalId(terminalId: String): ResponseEntity<LbandStatusResponse> {
        log.debug("Fetching status for L-Band terminal: $terminalId")

        // TODO: 실제 단말기 상태 조회 로직 구현
        val response = LbandStatusResponse(
            terminalId = terminalId,
            serviceStatus = LbandStatusResponse.ServiceStatus.ACTIVE,
            signalQuality = 75,
            dataUsage = LbandStatusResponseDataUsage(
                totalMb = 512.5,
                billingCycleStart = LocalDate.now().withDayOfMonth(1)
            )
        )
        return ResponseEntity.ok(response)
    }

    override fun postLbandServiceActivate(serviceActivationRequest: ServiceActivationRequest): ResponseEntity<LbandApiResponse> {
        println("Activating plan ${serviceActivationRequest.plan} for terminal ${serviceActivationRequest.terminalId}")

        // TODO: 실제 서비스 활성화 로직 구현
        val response = LbandApiResponse(
            message = "Service activated successfully",
            transactionId = UUID.randomUUID()
        )
        return ResponseEntity.ok(response)
    }
}