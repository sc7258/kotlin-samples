package com.sc7258.simpleorderhexagonal.adapter.`in`.web.dto

import java.time.LocalDateTime

data class OrderResponse(
    val orderId: Long,
    val totalAmount: Long,
    val createdAt: LocalDateTime,
    val orderLines: List<OrderLineDto>
) {
    data class OrderLineDto(
        val productId: Long,
        val price: Long,
        val quantity: Int
    )
}
