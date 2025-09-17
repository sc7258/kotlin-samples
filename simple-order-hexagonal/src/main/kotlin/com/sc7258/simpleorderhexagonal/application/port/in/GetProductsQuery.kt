package com.sc7258.simpleorderhexagonal.application.port.`in`

import com.sc7258.simpleorderhexagonal.domain.Product

interface GetProductsQuery {
    fun getProducts(): List<Product>
}
