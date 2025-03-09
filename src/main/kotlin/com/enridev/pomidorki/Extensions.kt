package com.enridev.pomidorki

import com.enridev.pomidorki.domain.CreateUserRequest
import com.enridev.pomidorki.domain.StatusUpdateRequest
import com.enridev.pomidorki.domain.dto.CreateUserRequestDto
import com.enridev.pomidorki.domain.dto.StatusDto
import com.enridev.pomidorki.domain.dto.StatusUpdateRequestDto
import com.enridev.pomidorki.domain.dto.UserDto
import com.enridev.pomidorki.domain.entities.StatusEntity
import com.enridev.pomidorki.domain.entities.UserEntity

fun StatusDto.toEntity(): StatusEntity {
    return StatusEntity(
        id = this.id,
        name = this.name
    )
}

fun StatusEntity.toDto(): StatusDto {
    return StatusDto(
        id = this.id,
        name = this.name
    )
}

fun StatusUpdateRequestDto.toStatusUpdateRequest(): StatusUpdateRequest {
    return StatusUpdateRequest(
        id = this.id,
        name = this.name
    )
}

fun UserEntity.toDto(): UserDto = UserDto(
    id = this.id,
    name = this.name,
    email = this.email,
    password = this.password,
    streakCount = this.streakCount,
    lastPomodoroDate = this.lastPomodoroDate,
    avatarUrl = this.avatarUrl,
    firebaseUid = this.firebaseUid,
    authMethod = this.authMethod,
    isActive = this.isActive,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)

fun CreateUserRequestDto.toCreateUserRequest(): CreateUserRequest {
    return CreateUserRequest(
        id = this.id,
        name = this.name,
        email = this.email,
        password = this.password,
        avatarUrl = this.avatarUrl,
        firebaseUid = this.firebaseUid,
        authMethod = this.authMethod
    )
}

fun CreateUserRequest.toCreateUserRequestDto(): CreateUserRequestDto {
    return CreateUserRequestDto(
        id = this.id,
        name = this.name,
        email = this.email,
        password = this.password,
        avatarUrl = this.avatarUrl,
        firebaseUid = this.firebaseUid,
        authMethod = this.authMethod
    )
}