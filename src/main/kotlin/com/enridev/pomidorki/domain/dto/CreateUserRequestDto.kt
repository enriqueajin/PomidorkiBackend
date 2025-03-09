package com.enridev.pomidorki.domain.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateUserRequestDto(

    val id: Long?,

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between {min} and {max} characters")
    val name: String,

    @NotBlank(message = "Email is required")
    @Size(min = 6, max = 150, message = "Email must be between {min} and {max} characters")
    val email: String,

    @Size(min = 8, max = 100, message = "Password must be between {min} and {max} characters")
    val password: String? = null,

    @Size(min = 12, max = 255, message = "Avatar URL must be between {min} and {max} characters")
    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg|jpeg|webp))", message = "Avatar URL must be a valid URL")
    val avatarUrl: String? = null,

    @NotBlank(message = "Firebase UID is required")
    @Size(max = 255, message = "Firebase UID must have maximum {max} characters")
    val firebaseUid: String,

    @NotBlank(message = "Auth method is required")
    @Size(min = 5, max = 25, message = "Auth method must be between {min} and {max} characters")
    val authMethod: String,
)
