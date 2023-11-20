package com.speerd_api.dto

class UserCreateDTO (
    val username: String,
    val password: String,
    val email: String,

)

data class RegistrationResponse(val message: String, val userId: Int)
