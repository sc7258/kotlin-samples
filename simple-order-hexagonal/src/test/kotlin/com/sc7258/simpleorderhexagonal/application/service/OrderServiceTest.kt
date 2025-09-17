package com.sc7258.simpleorderhexagonal.application.service

import com.sc7258.simpleorderhexagonal.application.port.`in`.PlaceOrderUseCase
import com.sc7258.simpleorderhexagonal.application.port.out.OrderRepositoryPort
import com.sc7258.simpleorderhexagonal.application.port.out.ProductRepositoryPort
import com.sc7258.simpleorderhexagonal.domain.Order
import com.sc7258.simpleorderhexagonal.domain.Product
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class OrderServiceTest {

    @MockK
    private lateinit var orderRepositoryPort: OrderRepositoryPort

    @MockK
    private lateinit var productRepositoryPort: ProductRepositoryPort

    @InjectMockKs
    private lateinit var orderService: OrderService

    @Test
    fun `placeOrder should succeed when product exists`() {
        // Arrange: Define the scenario
        val productId = 1L
        val command = PlaceOrderUseCase.PlaceOrderCommand(productId = productId, quantity = 2)
        val product = Product(id = productId, name = "Test Product", price = 1000)

        // Mocking: Tell the fake repositories how to behave
        every { productRepositoryPort.findById(productId) } returns product
        every { orderRepositoryPort.save(any()) } answers { firstArg() } // Return the same order that was passed in

        // Act: Execute the business logic
        val createdOrder = orderService.placeOrder(command)

        // Assert: Verify the results
        assertEquals(1, createdOrder.orderLines.size)
        assertEquals(productId, createdOrder.orderLines[0].productId)
        assertEquals(2000, createdOrder.totalAmount)

        // Verify that the save methods were called exactly once
        verify(exactly = 1) { productRepositoryPort.findById(productId) }
        verify(exactly = 1) { orderRepositoryPort.save(any()) }
    }

    @Test
    fun `placeOrder should throw exception when product does not exist`() {
        // Arrange: Define the scenario where the product is not found
        val nonExistentProductId = 999L
        val command = PlaceOrderUseCase.PlaceOrderCommand(productId = nonExistentProductId, quantity = 1)

        // Mocking: Tell the fake repository to return null
        every { productRepositoryPort.findById(nonExistentProductId) } returns null

        // Act & Assert: Check if the correct exception is thrown
        val exception = assertThrows<IllegalArgumentException> {
            orderService.placeOrder(command)
        }
        assertEquals("Product not found with id: $nonExistentProductId", exception.message)

        // Verify that the save method was never called
        verify(exactly = 0) { orderRepositoryPort.save(any()) }
    }
}
