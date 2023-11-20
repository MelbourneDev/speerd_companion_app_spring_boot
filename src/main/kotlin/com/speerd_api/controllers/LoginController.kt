package com.speerd_api.controllers

import com.speerd_api.dto.LoginDTO
import com.speerd_api.services.LoginService
import com.speerd_api.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class LoginController(private val loginService: LoginService,
                      private val userService: UserService
) {

    @PostMapping("/user")
    fun loginUser(@RequestBody loginRequest: LoginDTO): ResponseEntity<Any> {
        val userExists = loginService.validateUser(loginRequest.username, loginRequest.password)
        return if (userExists) {
            val userId = userService.getUserIdByUsername(loginRequest.username)
            ResponseEntity.ok(mapOf("message" to "User authenticated successfully", "userId" to userId))
        } else {
            ResponseEntity.badRequest().body(mapOf("message" to "Invalid username or password"))
        }
    }
}



