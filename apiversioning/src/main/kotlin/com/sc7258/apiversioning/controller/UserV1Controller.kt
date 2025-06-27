package com.sc7258.apiversioning.controller

import com.sc7258.apiversioning.generated.v1.api.UsersApi as UserApiV1
import com.sc7258.apiversioning.generated.v1.model.UserV1 as UserV1Model
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserV1Controller : UserApiV1 {

    override fun getAllUsersV1(): ResponseEntity<List<UserV1Model>> {
        println("Calling V1 API for all users (Kotlin via generated interface)")
        val user = UserV1Model(
            id = "1",
            name = "John Doe",
            email = "john.doe@example.com"
        )
        return ResponseEntity.ok(listOf(user))
    }

    override fun getUserByIdV1(id: String): ResponseEntity<UserV1Model> {
        println("Calling V1 API for user id: $id (Kotlin via generated interface)")
        return if (id == "1") {
            ResponseEntity.ok(UserV1Model(
                id = "1",
                name = "John Doe",
                email = "john.doe@example.com"
            ))
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
