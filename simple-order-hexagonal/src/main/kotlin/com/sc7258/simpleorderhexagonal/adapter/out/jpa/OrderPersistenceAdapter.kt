package com.sc7258.simpleorderhexagonal.adapter.out.jpa

import com.sc7258.simpleorderhexagonal.adapter.out.jpa.OrderJpaEntity
import com.sc7258.simpleorderhexagonal.adapter.out.jpa.OrderLineJpaEntity
import com.sc7258.simpleorderhexagonal.application.port.out.OrderRepositoryPort
import com.sc7258.simpleorderhexagonal.domain.Order
import com.sc7258.simpleorderhexagonal.domain.OrderLine
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class OrderPersistenceAdapter(
    private val orderJpaRepository: OrderJpaRepository
) : OrderRepositoryPort {

    override fun save(order: Order): Order {
        val orderJpaEntity = order.toJpaEntity()
        val savedEntity = orderJpaRepository.save(orderJpaEntity)
        return savedEntity.toDomain()
    }

    override fun findById(orderId: Long): Order? {
        return orderJpaRepository.findByIdOrNull(orderId)?.toDomain()
    }

    // --- Private Mapping Functions ---

    private fun Order.toJpaEntity(): OrderJpaEntity {
        val jpaEntity = OrderJpaEntity(
            id = this.id,
            totalAmount = this.totalAmount,
            createdAt = this.createdAt
        )
        this.orderLines.forEach { domainOrderLine ->
            val lineJpaEntity = OrderLineJpaEntity(
                id = null, // Always create new lines for a new order
                productId = domainOrderLine.productId,
                price = domainOrderLine.price,
                quantity = domainOrderLine.quantity
            )
            jpaEntity.addOrderLine(lineJpaEntity)
        }
        return jpaEntity
    }

    private fun OrderJpaEntity.toDomain(): Order {
        return Order(
            id = this.id,
            orderLines = this.orderLines.map { it.toDomain() },
            totalAmount = this.totalAmount,
            createdAt = this.createdAt
        )
    }

    private fun OrderLineJpaEntity.toDomain(): OrderLine {
        return OrderLine(
            productId = this.productId,
            price = this.price,
            quantity = this.quantity
        )
    }
}
