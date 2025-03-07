package com.enridev.pomidorki

import com.enridev.pomidorki.domain.entities.StatusEntity

fun testStatusEntityA(id: Int? = null): StatusEntity {
    return StatusEntity(
        id = id,
        name = "In progress"
    )
}