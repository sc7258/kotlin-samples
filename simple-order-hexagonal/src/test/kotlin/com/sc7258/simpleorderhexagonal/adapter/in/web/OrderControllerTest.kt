package com.sc7258.simpleorderhexagonal.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.sc7258.simpleorderhexagonal.application.port.out.ProductRepositoryPort
import com.sc7258.simpleorderhexagonal.domain.Product
import com.sc7258.simpleorderhexagonal.generated.model.CreateOrderRequest
import com.sc7258.simpleorderhexagonal.generated.model.OrderResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@EnableJpaRepositories("com.sc7258.simpleorderhexagonal.adapter.out.jpa")
@EntityScan("com.sc7258.simpleorderhexagonal.adapter.out.jpa") // Correct path for ALL entities
class OrderControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val productRepositoryPort: ProductRepositoryPort,
) {

    private lateinit var product: Product

    @BeforeEach
    fun setUp() {
        product = productRepositoryPort.save(Product(name = "Test Product", price = 1000))
    }

    @Test
    fun `createOrder and getOrderById should succeed`() {
        // 1. Create Order
        val createRequest = CreateOrderRequest(productId = product.id!!, quantity = 2)

        val createResult = mockMvc.post("/orders") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(createRequest)
        }.andExpect {
            status { isCreated() }
        }.andReturn()

        val createdOrderResponse = objectMapper.readValue(
            createResult.response.contentAsString,
            OrderResponse::class.java
        )
        val orderId = createdOrderResponse.orderId

        // 2. Get Order by ID
        mockMvc.get("/orders/$orderId") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.orderId") { value(orderId) }
        }
    }

    @Test
    fun `getOrderById with non-existent id should return 404`() {
        val nonExistentId = 999L

        mockMvc.get("/orders/$nonExistentId") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNotFound() }
        }
    }
}
