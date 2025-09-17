package com.sc7258.simpleorderhexagonal.application.port.`in`

import com.sc7258.simpleorderhexagonal.domain.Order

interface PlaceOrderUseCase {
    fun placeOrder(command: PlaceOrderCommand): Order

    data class PlaceOrderCommand(
        val orderLines: List<OrderLineCommand>
    )

    data class OrderLineCommand(
        val productId: Long,
        val price: Long,
        val quantity: Int
    )
}
