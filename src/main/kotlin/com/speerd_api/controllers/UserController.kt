package com.speerd_api.controllers

import com.speerd_api.dto.ApiResponse
import com.speerd_api.dto.RegistrationResponse
import com.speerd_api.dto.UpdateUserDTO
import com.speerd_api.dto.UserCreateDTO
import com.speerd_api.models.ApplicationUser
import com.speerd_api.repositories.ApplicationUserRepository
import com.speerd_api.services.ValidationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/register")
class LoginUserController(

    @Autowired private val validationService: ValidationService,
    @Autowired private val userRepository: ApplicationUserRepository
) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(LoginUserController::class.java)
    }

    @PostMapping("/user")
    fun createUser(@RequestBody userDto: UserCreateDTO): ResponseEntity<RegistrationResponse> {
        // Check if the username or email already exists
        if (userRepository.existsByUsername(userDto.username)) {
            logger.warn("Attempted registration with existing username: ${userDto.username}")
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RegistrationResponse("Username already exists", -1))
        }

        if (userRepository.existsByEmail(userDto.email)) {
            logger.warn("Attempted registration with existing email: ${userDto.email}")
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RegistrationResponse("Email already exists", -1))
        }

        if (!validationService.validatePassword(userDto.password)) {
            logger.warn("Invalid password format for user: ${userDto.username}")
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RegistrationResponse("Invalid password format", -1))
        }

        // Proceed with creating the new user
        val user = ApplicationUser(
            username = userDto.username,
            password = userDto.password,
            email = userDto.email,
        )

        val savedUser = userRepository.save(user)
        logger.info("User created successfully: ${userDto.username}")

        return ResponseEntity.ok(RegistrationResponse("User created successfully", savedUser.id))
    }


    @GetMapping("/details/{userId}")
    fun getUserDetails(@PathVariable userId: Int): ResponseEntity<Any> {
        val user = userRepository.findById(userId)
        return if (user.isPresent) {
            val userDetails = user.get()
            ResponseEntity.ok(UserCreateDTO(userDetails.username, userDetails.password, userDetails.email))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found")
        }
    }



    // PUT endpoint to update user details
    @PutMapping("/update/{userId}")
    fun updateUser(@PathVariable userId: Int, @RequestBody updateUserDTO: UpdateUserDTO): ResponseEntity<ApiResponse> {
        val existingUser = userRepository.findById(userId)
        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            // Check if new username or email already exists for another user
            if (userRepository.existsByUsernameAndIdIsNot(updateUserDTO.username, userId)) {
                return ResponseEntity.badRequest().body(ApiResponse("Username already exists"))
            }
            if (userRepository.existsByEmailAndIdIsNot(updateUserDTO.email, userId)) {
                return ResponseEntity.badRequest().body(ApiResponse("Email already exists"))
            }
            // Create a new ApplicationUser with updated properties
            val updatedUser = ApplicationUser(
                id = userToUpdate.id,
                username = updateUserDTO.username,
                password = updateUserDTO.password,
                email = updateUserDTO.email,
                role = userToUpdate.role
            )

            userRepository.save(updatedUser)
            ResponseEntity.ok(ApiResponse("User updated successfully"))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse("User not found"))
        }
    }

    }



