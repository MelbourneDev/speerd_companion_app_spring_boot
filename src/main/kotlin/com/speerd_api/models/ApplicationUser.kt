package com.speerd_api.models

import javax.persistence.*

@Entity
@Table(name = "userlogins")
class ApplicationUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(length = 50)
    val username: String,

    @Column
    val password: String,

    @Column(length = 100)
    val email: String,

    @Column(length = 50)
    val role: String = "User", //default role set to user as admin appended through backend db

)
