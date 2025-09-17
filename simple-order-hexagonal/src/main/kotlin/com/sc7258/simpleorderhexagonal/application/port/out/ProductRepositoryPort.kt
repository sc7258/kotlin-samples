package com.sc7258.simpleorderhexagonal.application.port.out

import com.sc7258.simpleorderhexagonal.domain.Product

interface ProductRepositoryPort {
    fun findById(productId: Long): Product?
    fun save(product: Product): Product
    fun findAll(): List<Product>
}
