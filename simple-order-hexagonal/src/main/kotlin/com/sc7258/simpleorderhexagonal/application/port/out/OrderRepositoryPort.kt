package com.sc7258.simpleorderhexagonal.application.port.out

import com.sc7258.simpleorderhexagonal.domain.Order

interface OrderRepositoryPort {
    fun save(order: Order): Order
    fun findById(orderId: Long): Order?
}
