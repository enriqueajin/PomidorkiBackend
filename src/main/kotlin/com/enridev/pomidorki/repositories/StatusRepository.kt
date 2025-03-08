package com.enridev.pomidorki.repositories

import com.enridev.pomidorki.domain.entities.StatusEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StatusRepository: JpaRepository<StatusEntity, Int>