package com.enridev.pomidorki.domain.dto

import java.time.OffsetDateTime

data class UserDto(
    val id: Long?,
    val name: String,
    val email: String,
    val password: String,
    val streakCount: Int,
    val lastPomodoroDate: OffsetDateTime,
    val avatarUrl: String,
    val firebaseUid: String,
    val authMethod: String,
    val isActive: Boolean,
    var createdAt: OffsetDateTime,
    var updatedAt: OffsetDateTime,
)