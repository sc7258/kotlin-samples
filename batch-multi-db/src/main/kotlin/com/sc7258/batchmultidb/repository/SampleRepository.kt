package com.sc7258.batchmultidb.repository

import com.sc7258.batchmultidb.entity.SampleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<SampleEntity, Long>