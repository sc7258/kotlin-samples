package com.sc7258.simpleorderhexagonal.adapter.`in`.web.mapper

import com.sc7258.simpleorderhexagonal.application.port.`in`.PlaceOrderUseCase
import com.sc7258.simpleorderhexagonal.domain.Order
import com.sc7258.simpleorderhexagonal.domain.Product
import com.sc7258.simpleorderhexagonal.generated.model.CreateOrderRequest
import com.sc7258.simpleorderhexagonal.generated.model.OrderResponse
import com.sc7258.simpleorderhexagonal.generated.model.ProductResponse
import org.springframework.stereotype.Component

@Component // No more MapStruct magic. This is now a plain, reliable Spring component.
class ApiMapper {

    fun toCommand(request: CreateOrderRequest): PlaceOrderUseCase.PlaceOrderCommand {
        return PlaceOrderUseCase.PlaceOrderCommand(
            productId = request.productId,
            quantity = request.quantity
        )
    }

    fun toResponse(order: Order): OrderResponse {
        return OrderResponse(
            orderId = order.id!!.toString()
        )
    }

    private fun toProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price
        )
    }

    fun toProductResponseList(products: List<Product>): List<ProductResponse> {
        return products.map { toProductResponse(it) }
    }
}
