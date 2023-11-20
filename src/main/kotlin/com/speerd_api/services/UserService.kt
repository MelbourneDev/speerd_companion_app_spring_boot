package com.speerd_api.services


import com.speerd_api.repositories.ApplicationUserRepository
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: ApplicationUserRepository) {

    fun getUserIdByUsername(username: String): Int {
        val user = userRepository.findByUsername(username)
        return user?.id ?: 0
    }


}

