package com.sc7258.simpleorderhexagonal.adapter.out.jpa

import com.sc7258.simpleorderhexagonal.adapter.out.jpa.entity.OrderJpaEntity
import com.sc7258.simpleorderhexagonal.domain.Order
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class JpaMapper {

    companion object {
        val INSTANCE: JpaMapper = Mappers.getMapper(JpaMapper::class.java)
    }

    abstract fun toDomain(entity: OrderJpaEntity): Order

    abstract fun toJpaEntity(domain: Order): OrderJpaEntity

    @Suppress("unused")
    @AfterMapping
    fun setOrderInLines(@MappingTarget orderJpaEntity: OrderJpaEntity) {
        orderJpaEntity.orderLines.forEach { it.order = orderJpaEntity }
    }
}