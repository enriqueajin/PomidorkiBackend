package com.enridev.pomidorki.services

import com.enridev.pomidorki.domain.StatusUpdateRequest
import com.enridev.pomidorki.domain.entities.StatusEntity

interface StatusService {

    fun list(): List<StatusEntity>
    fun create(statusEntity: StatusEntity): StatusEntity
    fun get(statusId: Int): StatusEntity?
    fun fullUpdate(statusId: Int, statusEntity: StatusEntity): StatusEntity
    fun partialUpdate(statusId: Int, statusUpdateRequest: StatusUpdateRequest): StatusEntity
    fun delete(statusId: Int)
}