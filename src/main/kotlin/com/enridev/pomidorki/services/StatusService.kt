package com.enridev.pomidorki.services

import com.enridev.pomidorki.domain.entities.StatusEntity

interface StatusService {

    fun list(): List<StatusEntity>
    fun create(statusEntity: StatusEntity): StatusEntity
}