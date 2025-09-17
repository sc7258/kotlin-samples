package com.sc7258.simpleorderhexagonal.adapter.out.jpa

import com.sc7258.simpleorderhexagonal.adapter.out.jpa.OrderJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<OrderJpaEntity, Long>
