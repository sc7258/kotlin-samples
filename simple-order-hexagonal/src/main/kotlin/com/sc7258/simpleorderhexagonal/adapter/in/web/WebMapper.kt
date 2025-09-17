package com.sc7258.simpleorderhexagonal.adapter.`in`.web

import com.sc7258.simpleorderhexagonal.adapter.`in`.web.dto.OrderResponse
import com.sc7258.simpleorderhexagonal.adapter.`in`.web.dto.PlaceOrderRequest
import com.sc7258.simpleorderhexagonal.application.port.`in`.PlaceOrderUseCase
import com.sc7258.simpleorderhexagonal.domain.Order
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class WebMapper {

    companion object {
        val INSTANCE: WebMapper = Mappers.getMapper(WebMapper::class.java)
    }

    abstract fun toCommand(request: PlaceOrderRequest): PlaceOrderUseCase.PlaceOrderCommand

    @Mapping(source = "id", target = "orderId")
    abstract fun toResponse(order: Order): OrderResponse
}
