package com.enridev.pomidorki.services.impl

import com.enridev.pomidorki.domain.entities.StatusEntity
import com.enridev.pomidorki.repositories.StatusRepository
import com.enridev.pomidorki.services.StatusService
import org.springframework.stereotype.Service

@Service
class StatusServiceImpl(private val statusRepository: StatusRepository): StatusService {
    override fun list(): List<StatusEntity> = statusRepository.findAll()
}