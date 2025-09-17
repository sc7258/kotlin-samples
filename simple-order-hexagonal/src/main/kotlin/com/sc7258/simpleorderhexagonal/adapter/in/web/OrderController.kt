package com.sc7258.simpleorderhexagonal.adapter.`in`.web

import com.sc7258.simpleorderhexagonal.adapter.`in`.web.mapper.ApiMapper
import com.sc7258.simpleorderhexagonal.application.port.`in`.GetOrderQuery
import com.sc7258.simpleorderhexagonal.application.port.`in`.PlaceOrderUseCase
import com.sc7258.simpleorderhexagonal.generated.api.OrdersApi
import com.sc7258.simpleorderhexagonal.generated.model.CreateOrderRequest
import com.sc7258.simpleorderhexagonal.generated.model.OrderResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val placeOrderUseCase: PlaceOrderUseCase,
    private val getOrderQuery: GetOrderQuery,
    private val apiMapper: ApiMapper,
) : OrdersApi {

    override fun createOrder(createOrderRequest: CreateOrderRequest): ResponseEntity<OrderResponse> {
        val command = apiMapper.toCommand(createOrderRequest)
        val order = placeOrderUseCase.placeOrder(command)
        val response = apiMapper.toResponse(order)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    override fun getOrderById(id: Long): ResponseEntity<OrderResponse> {
        val query = GetOrderQuery.GetOrderQuery(id)
        val order = getOrderQuery.getOrder(query)
        return order?.let {
            ResponseEntity.ok(apiMapper.toResponse(it))
        } ?: ResponseEntity.notFound().build()
    }
}
