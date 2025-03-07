package com.enridev.pomidorki

import com.enridev.pomidorki.domain.dto.StatusUpdateRequestDto
import com.enridev.pomidorki.domain.entities.StatusEntity

fun testStatusEntityA(id: Int? = null): StatusEntity {
    return StatusEntity(
        id = id,
        name = "In progress"
    )
}

fun testStatusRequestDtoA(id: Int? = null): StatusUpdateRequestDto {
    return StatusUpdateRequestDto(
        id = id,
        name = "In QA"
    )
}