package com.sc7258.batchmultidb.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_sample")
data class SampleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String = ""
)