package com.speerd_api.repositories

import com.speerd_api.models.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ApplicationUserRepository : JpaRepository<ApplicationUser, Int> {
    fun findByUsername(username: String): ApplicationUser?
    fun findByUsernameAndPassword(username: String, password: String): Optional<ApplicationUser>
    fun existsByUsernameAndIdIsNot(username: String, userId: Int): Boolean
    fun existsByEmailAndIdIsNot(email: String, userId: Int): Boolean
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}