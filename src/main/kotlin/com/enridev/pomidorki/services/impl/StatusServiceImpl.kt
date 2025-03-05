package com.enridev.pomidorki.services.impl

import com.enridev.pomidorki.domain.StatusUpdateRequest
import com.enridev.pomidorki.domain.entities.StatusEntity
import com.enridev.pomidorki.repositories.StatusRepository
import com.enridev.pomidorki.services.StatusService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StatusServiceImpl(private val statusRepository: StatusRepository): StatusService {

    override fun list(): List<StatusEntity> = statusRepository.findAll()

    override fun create(statusEntity: StatusEntity): StatusEntity {
        require(statusEntity.id == null)
        return statusRepository.save(statusEntity)
    }

    override fun get(statusId: Int): StatusEntity? = statusRepository.findByIdOrNull(statusId)

    @Transactional
    override fun fullUpdate(statusId: Int, statusEntity: StatusEntity): StatusEntity {
        check(statusRepository.existsById(statusId))
        val normalisedStatus = statusEntity.copy(id = statusId)
        return statusRepository.save(normalisedStatus)
    }

    @Transactional
    override fun partialUpdate(statusId: Int, statusUpdateRequest: StatusUpdateRequest): StatusEntity {
        val retrievedStatus = statusRepository.findByIdOrNull(statusId)
        checkNotNull(retrievedStatus)
        val updatedStatus = retrievedStatus.copy(
            name = statusUpdateRequest.name ?: retrievedStatus.name
        )
        return statusRepository.save(updatedStatus)
    }
}