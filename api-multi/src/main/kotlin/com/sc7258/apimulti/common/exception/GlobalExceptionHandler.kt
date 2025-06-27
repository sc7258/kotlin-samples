package com.sc7258.apimulti.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.OffsetDateTime

/**
 * A custom error response structure for consistent error reporting.
 */
data class ErrorResponse(
    val timestamp: OffsetDateTime,
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)

/**
 * A central place to handle exceptions across the whole application.
 * This captures exceptions from any controller and formats the response consistently.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * Handles cases where a requested resource is not found (e.g., an entity with a specific ID).
     * Returns a 404 Not Found response.
     */
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException, request: WebRequest): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(
            status = HttpStatus.NOT_FOUND,
            message = ex.message ?: "The requested resource was not found.",
            request = request
        )
    }

    /**
     * Handles generic, unexpected exceptions.
     * Returns a 500 Internal Server Error to avoid exposing internal details.
     */
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        // It's a good practice to log the full exception here
        // log.error("Unhandled exception occurred", ex)
        return buildErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            message = "An unexpected internal error occurred.",
            request = request
        )
    }

    private fun buildErrorResponse(status: HttpStatus, message: String, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            timestamp = OffsetDateTime.now(),
            status = status.value(),
            error = status.reasonPhrase,
            message = message,
            path = request.getDescription(false).substringAfter("uri=") // Extracts the path
        )
        return ResponseEntity(errorResponse, status)
    }
}