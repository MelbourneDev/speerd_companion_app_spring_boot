package com.speerd_api.services

import org.springframework.stereotype.Service

@Service
class ValidationService {
    fun validatePassword(password: String): Boolean {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\da-zA-Z]).{8,}$".toRegex())
    }
}
