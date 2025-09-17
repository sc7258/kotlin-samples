package com.sc7258.simpleorderhexagonal.application.port.`in`

import com.sc7258.simpleorderhexagonal.domain.Order

interface GetOrderQuery {
    fun getOrder(query: GetOrderQuery): Order?

    data class GetOrderQuery(
        val orderId: Long
    )
}
