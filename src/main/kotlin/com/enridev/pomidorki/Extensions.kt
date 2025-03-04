package com.enridev.pomidorki

import com.enridev.pomidorki.domain.dto.StatusDto
import com.enridev.pomidorki.domain.entities.StatusEntity

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