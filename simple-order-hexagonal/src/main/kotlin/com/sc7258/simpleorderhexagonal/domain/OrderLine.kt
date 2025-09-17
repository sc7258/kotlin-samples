package com.sc7258.simpleorderhexagonal.domain

data class OrderLine(
    val id: Long? = null,
    val productId: Long,
    val price: Long,
    val quantity: Int,
)
