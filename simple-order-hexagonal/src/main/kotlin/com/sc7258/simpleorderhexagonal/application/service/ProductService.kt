package com.sc7258.simpleorderhexagonal.application.service

import com.sc7258.simpleorderhexagonal.application.port.`in`.GetProductsQuery
import com.sc7258.simpleorderhexagonal.application.port.out.ProductRepositoryPort
import com.sc7258.simpleorderhexagonal.domain.Product
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true) // Read-only transaction for query operations
class ProductService(
    private val productRepositoryPort: ProductRepositoryPort
) : GetProductsQuery {

    override fun getProducts(): List<Product> {
        return productRepositoryPort.findAll()
    }
}
