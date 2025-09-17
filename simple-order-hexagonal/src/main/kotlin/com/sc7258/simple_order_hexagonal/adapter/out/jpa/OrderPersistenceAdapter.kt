package com.sc7258.simpleorderhexagonal.adapter.out.jpa

import com.sc7258.simpleorderhexagonal.application.port.out.OrderRepositoryPort
import com.sc7258.simpleorderhexagonal.domain.Order
import org.springframework.stereotype.Repository

@Repository
class OrderPersistenceAdapter(
    private val orderJpaRepository: OrderJpaRepository
) : OrderRepositoryPort {

    private val jpaMapper = JpaMapper.INSTANCE

    override fun save(order: Order): Order {
        val entity = jpaMapper.toJpaEntity(order)
        val savedEntity = orderJpaRepository.save(entity)
        return jpaMapper.toDomain(savedEntity)
    }

    override fun findById(orderId: Long): Order? {
        val entity = orderJpaRepository.findById(orderId).orElse(null)
        return entity?.let { jpaMapper.toDomain(it) }
    }
}
