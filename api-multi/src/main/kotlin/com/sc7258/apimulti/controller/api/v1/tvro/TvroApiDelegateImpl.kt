package com.sc7258.apimulti.controller.api.v1.tvro

import com.sc7258.apimulti.api.tvro.TvroApiDelegate
import com.sc7258.apimulti.model.tvro.TvroApiResponse
import com.sc7258.apimulti.model.tvro.TvroCommandRequest
import com.sc7258.apimulti.model.tvro.TvroStatusResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service // Spring이 이 클래스를 Bean으로 인식하도록 @Service 또는 @RestController 어노테이션을 추가합니다.
class TvroApiDelegateImpl : TvroApiDelegate {

    /**
     * GET /tvro/status/{antennaId}
     * 안테나 상태를 조회하는 비즈니스 로직을 구현합니다.
     */
    override fun getTvroStatusByAntennaId(antennaId: String): ResponseEntity<TvroStatusResponse> {
        println("Fetching status for TVRO antenna: $antennaId")

        // TODO: 실제 데이터베이스나 외부 서비스에서 안테나 상태를 조회하는 로직 구현
        val response = TvroStatusResponse(
            antennaId = antennaId,
            signalStrength = -50,
            status = TvroStatusResponse.Status.TRACKING,
            azimuth = 180.5,
            elevation = 45.2
        )
        return ResponseEntity.ok(response)
    }

    /**
     * POST /tvro/command
     * 안테나에 명령을 전송하는 비즈니스 로직을 구현합니다.
     */
    override fun postTvroCommand(tvroCommandRequest: TvroCommandRequest): ResponseEntity<TvroApiResponse> {
        println("Received command for antenna ${tvroCommandRequest.antennaId}: ${tvroCommandRequest.command}")

        // TODO: 실제 안테나에 명령을 전달하는 로직 구현
        val response = TvroApiResponse(
            message = "Command accepted",
            timestamp = OffsetDateTime.now()
        )
        return ResponseEntity.accepted().body(response)
    }
}