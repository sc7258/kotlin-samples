package com.sc7258.simpleorderhexagonal.adapter.`in`.web.dto

data class PlaceOrderRequest(
    val orderLines: List<OrderLineDto>
) {
    data class OrderLineDto(
        val productId: Long,
        val price: Long,
        val quantity: Int
    )
}
