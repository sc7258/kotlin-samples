package com.sc7258.apiversioning.controller

import com.sc7258.apiversioning.generated.v2.api.UsersApi as UserApiV2
import com.sc7258.apiversioning.generated.v2.model.UserV2 as UserV2Model
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
// [IMPROVEMENT] Add a class-level RequestMapping to define the base path for v2 APIs.
@RequestMapping("/api/v2")
class UserV2Controller : UserApiV2 {

    // [IMPROVEMENT] Use a proper logger instead of println().
    private val log = LoggerFactory.getLogger(javaClass)

    override fun getAllUsersV2(): ResponseEntity<List<UserV2Model>> {
        log.info("Calling V2 API to get all users")
        val user = UserV2Model(
            id = "1",
            name =  "Jane Doe",
            email = "jane.doe@example.com",
            age =  30
        )
        return ResponseEntity.ok(listOf(user))
    }

    override fun getUserByIdV2(id: String): ResponseEntity<UserV2Model> {
        log.info("Calling V2 API for user id: {}", id)
        return if (id == "1") {
            val user = UserV2Model(
                id = "1",
                name =  "Jane Doe",
                email = "jane.doe@example.com",
                age =  30
            )
            ResponseEntity.ok(user)
        } else {
            log.warn("User not found for id: {}", id)
            ResponseEntity.notFound().build()
        }
    }
}