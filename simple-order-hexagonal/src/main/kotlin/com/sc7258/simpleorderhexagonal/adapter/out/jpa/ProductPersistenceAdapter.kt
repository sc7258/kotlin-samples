package com.sc7258.simpleorderhexagonal.adapter.out.jpa

import com.sc7258.simpleorderhexagonal.application.port.out.ProductRepositoryPort
import com.sc7258.simpleorderhexagonal.domain.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ProductPersistenceAdapter(
    private val productJpaRepository: ProductJpaRepository
) : ProductRepositoryPort {

    override fun findById(productId: Long): Product? {
        return productJpaRepository.findByIdOrNull(productId)?.toDomain()
    }

    override fun save(product: Product): Product {
        val entity = ProductJpaEntity(
            id = product.id,
            name = product.name,
            price = product.price
        )
        return productJpaRepository.save(entity).toDomain()
    }

    override fun findAll(): List<Product> {
        return productJpaRepository.findAll().map { it.toDomain() }
    }

    private fun ProductJpaEntity.toDomain(): Product {
        return Product(
            id = this.id!!,
            name = this.name,
            price = this.price
        )
    }
}
