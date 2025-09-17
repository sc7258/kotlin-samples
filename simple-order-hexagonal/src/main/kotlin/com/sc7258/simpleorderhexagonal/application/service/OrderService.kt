package com.sc7258.simpleorderhexagonal.application.service

import com.sc7258.simpleorderhexagonal.application.port.`in`.GetOrderQuery
import com.sc7258.simpleorderhexagonal.application.port.`in`.PlaceOrderUseCase
import com.sc7258.simpleorderhexagonal.application.port.out.OrderRepositoryPort
import com.sc7258.simpleorderhexagonal.application.port.out.ProductRepositoryPort
import com.sc7258.simpleorderhexagonal.domain.Order
import com.sc7258.simpleorderhexagonal.domain.OrderLine
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderService(
    private val orderRepositoryPort: OrderRepositoryPort,
    private val productRepositoryPort: ProductRepositoryPort, // Use the port interface
) : PlaceOrderUseCase, GetOrderQuery {

    override fun placeOrder(command: PlaceOrderUseCase.PlaceOrderCommand): Order {
        val product = productRepositoryPort.findById(command.productId)
            ?: throw IllegalArgumentException("Product not found with id: ${command.productId}")

        val orderLine = OrderLine(
            productId = product.id!!,
            price = product.price, // Use the price from the database
            quantity = command.quantity
        )

        val order = Order.create(listOf(orderLine))
        return orderRepositoryPort.save(order)
    }

    @Transactional(readOnly = true)
    override fun getOrder(query: GetOrderQuery.GetOrderQuery): Order? {
        return orderRepositoryPort.findById(query.orderId)
    }
}
