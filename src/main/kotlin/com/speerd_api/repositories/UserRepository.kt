package com.speerd_api.repositories


import com.speerd_api.models.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<ApplicationUser, String> {
    fun findByUsername(username: String): ApplicationUser?

}
