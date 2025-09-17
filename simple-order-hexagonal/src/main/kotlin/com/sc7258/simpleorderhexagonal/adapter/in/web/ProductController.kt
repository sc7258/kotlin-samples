package com.sc7258.simpleorderhexagonal.adapter.`in`.web

import com.sc7258.simpleorderhexagonal.adapter.`in`.web.mapper.ApiMapper
import com.sc7258.simpleorderhexagonal.application.port.`in`.GetProductsQuery
import com.sc7258.simpleorderhexagonal.generated.api.ProductsApi
import com.sc7258.simpleorderhexagonal.generated.model.ProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val getProductsQuery: GetProductsQuery,
    private val apiMapper: ApiMapper,
) : ProductsApi {

    override fun getProducts(): ResponseEntity<List<ProductResponse>> {
        val products = getProductsQuery.getProducts()
        val response = apiMapper.toProductResponseList(products)
        return ResponseEntity.ok(response)
    }
}
