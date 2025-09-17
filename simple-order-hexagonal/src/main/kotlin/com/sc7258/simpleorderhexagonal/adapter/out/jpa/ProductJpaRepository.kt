package com.sc7258.simpleorderhexagonal.adapter.out.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<ProductJpaEntity, Long>
