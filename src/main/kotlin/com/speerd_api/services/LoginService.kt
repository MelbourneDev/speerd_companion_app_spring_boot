package com.speerd_api.services

import com.speerd_api.repositories.ApplicationUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginService(@Autowired private val userRepository: ApplicationUserRepository) {

    fun validateUser(username: String, password: String): Boolean {
        val user = userRepository.findByUsernameAndPassword(username, password)
        return user.isPresent
    }
}
