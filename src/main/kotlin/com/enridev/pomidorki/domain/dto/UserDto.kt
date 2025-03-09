package com.enridev.pomidorki.domain.dto

data class UserDto(
    val id: Long?,
    val name: String,
    val email: String,
    val password: String,
    val streakCount: Int,
    val lastPomodoroDate: String,
    val avatarUrl: String,
    val firebaseUid: String,
    val isActive: Boolean
)