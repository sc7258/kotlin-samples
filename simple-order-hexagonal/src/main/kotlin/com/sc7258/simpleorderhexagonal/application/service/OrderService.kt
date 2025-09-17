package com.sc7258.simpleorderhexagonal.application.service

import com.sc7258.simpleorderhexagonal.application.port.`in`.GetOrderQuery
import com.sc7258.simpleorderhexagonal.application.port.`in`.PlaceOrderUseCase
import com.sc7258.simpleorderhexagonal.application.port.out.OrderRepositoryPort
import com.sc7258.simpleorderhexagonal.domain.Order
import com.sc7258.simpleorderhexagonal.domain.OrderLine
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderService(
    private val orderRepositoryPort: OrderRepositoryPort
) : PlaceOrderUseCase, GetOrderQuery {

    override fun placeOrder(command: PlaceOrderUseCase.PlaceOrderCommand): Order {
        val orderLines = command.orderLines.map {
            OrderLine(
                productId = it.productId,
                price = it.price,
                quantity = it.quantity
            )
        }
        val order = Order.create(orderLines)
        return orderRepositoryPort.save(order)
    }

    @Transactional(readOnly = true)
    override fun getOrder(query: GetOrderQuery.GetOrderQuery): Order? {
        return orderRepositoryPort.findById(query.orderId)
    }
}
