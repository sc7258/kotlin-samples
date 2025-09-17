package com.sc7258.simpleorderhexagonal.domain

import java.time.LocalDateTime

data class Order(
    val id: Long? = null,
    val orderLines: List<OrderLine>,
    val totalAmount: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun create(orderLines: List<OrderLine>): Order {
            val totalAmount = orderLines.sumOf { it.price * it.quantity }
            return Order(
                orderLines = orderLines,
                totalAmount = totalAmount,
            )
        }
    }
}
