package com.speerd_api.dto

data class UpdateUserDTO(
    val id: Int,
    var username: String,
    var email: String,
    var password: String
)

data class ApiResponse(val message: String)