package com.sc7258.simpleorderhexagonal.adapter.`in`.web

import com.sc7258.simpleorderhexagonal.adapter.`in`.web.dto.OrderResponse
import com.sc7258.simpleorderhexagonal.adapter.`in`.web.dto.PlaceOrderRequest
import com.sc7258.simpleorderhexagonal.application.port.`in`.GetOrderQuery
import com.sc7258.simpleorderhexagonal.application.port.`in`.PlaceOrderUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val placeOrderUseCase: PlaceOrderUseCase,
    private val getOrderQuery: GetOrderQuery,
) {

    private val webMapper = WebMapper.INSTANCE

    @PostMapping
    fun placeOrder(@RequestBody request: PlaceOrderRequest): OrderResponse {
        val command = webMapper.toCommand(request)
        val order = placeOrderUseCase.placeOrder(command)
        return webMapper.toResponse(order)
    }

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long): ResponseEntity<OrderResponse> {
        val query = GetOrderQuery.GetOrderQuery(id)
        val order = getOrderQuery.getOrder(query)
        return order?.let {
            ResponseEntity.ok(webMapper.toResponse(it))
        } ?: ResponseEntity.notFound().build()
    }
}
