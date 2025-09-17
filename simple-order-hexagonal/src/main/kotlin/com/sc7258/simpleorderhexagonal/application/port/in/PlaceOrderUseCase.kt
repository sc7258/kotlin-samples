package com.sc7258.simpleorderhexagonal.application.port.`in`

import com.sc7258.simpleorderhexagonal.domain.Order

interface PlaceOrderUseCase {
    fun placeOrder(command: PlaceOrderCommand): Order

    data class PlaceOrderCommand(
        val productId: Long,
        val quantity: Int
    )
}
