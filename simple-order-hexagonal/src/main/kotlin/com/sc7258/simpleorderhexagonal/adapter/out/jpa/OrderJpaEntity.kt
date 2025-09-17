package com.sc7258.simpleorderhexagonal.adapter.out.jpa

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orderLines: MutableList<OrderLineJpaEntity> = mutableListOf(),

    val totalAmount: Long,

    val createdAt: LocalDateTime,
) {
    fun addOrderLine(orderLine: OrderLineJpaEntity) {
        orderLines.add(orderLine)
        orderLine.order = this
    }
}
