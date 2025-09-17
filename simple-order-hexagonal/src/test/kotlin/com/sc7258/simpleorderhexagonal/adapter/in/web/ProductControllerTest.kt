package com.sc7258.simpleorderhexagonal.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.sc7258.simpleorderhexagonal.adapter.out.jpa.ProductJpaRepository
import com.sc7258.simpleorderhexagonal.application.port.out.ProductRepositoryPort
import com.sc7258.simpleorderhexagonal.domain.Product
import com.sc7258.simpleorderhexagonal.generated.model.ProductResponse
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
@EnableJpaRepositories("com.sc7258.simpleorderhexagonal.adapter.out.jpa")
@EntityScan("com.sc7258.simpleorderhexagonal.adapter.out.jpa")
class ProductControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val productRepositoryPort: ProductRepositoryPort,
    private val objectMapper: ObjectMapper,
    private val productJpaRepository: ProductJpaRepository, // Inject repository for manual cleanup
) {

    @AfterEach
    fun tearDown() {
        // Clean up the database manually after each test to ensure isolation
        productJpaRepository.deleteAll()
    }

    @Test
    fun `getProducts should return list of products`() {
        // 1. Setup: Create some products. Without @Transactional, this will be committed immediately.
        val product1 = productRepositoryPort.save(Product(name = "Test Laptop", price = 1500L))
        val product2 = productRepositoryPort.save(Product(name = "Test Mouse", price = 50L))

        // 2. Action: Call the endpoint. It can now see the committed data.
        val mvcResult = mockMvc.get("/products") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn()

        // 3. Assertions: Manually deserialize and verify the response content
        val responseString = mvcResult.response.contentAsString
        val productResponses = objectMapper.readValue(responseString, Array<ProductResponse>::class.java).toList()

        // Verify the size of the list
        assertEquals(2, productResponses.size)

        // Verify the content of the first product (order-independent)
        val laptop = productResponses.find { it.name == "Test Laptop" }
        assertNotNull(laptop)
        assertEquals(product1.id, laptop!!.id)
        assertEquals(1500L, laptop.price)

        // Verify the content of the second product (order-independent)
        val mouse = productResponses.find { it.name == "Test Mouse" }
        assertNotNull(mouse)
        assertEquals(product2.id, mouse!!.id)
        assertEquals(50L, mouse.price)
    }
}
