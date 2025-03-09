package com.enridev.pomidorki.domain

data class CreateUserRequest(
    val id: Long?,
    val name: String,
    val email: String,
    val password: String?,
    val avatarUrl: String?,
    val firebaseUid: String,
    val authMethod: String,
)
