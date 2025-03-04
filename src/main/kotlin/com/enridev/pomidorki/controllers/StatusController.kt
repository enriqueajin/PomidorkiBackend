package com.enridev.pomidorki.controllers

import com.enridev.pomidorki.domain.dto.StatusDto
import com.enridev.pomidorki.services.StatusService
import com.enridev.pomidorki.toDto
import com.enridev.pomidorki.toEntity
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/v1/status"])
class StatusController(private val statusService: StatusService) {

    @GetMapping
    fun readManyStatus(): List<StatusDto> {
        return statusService.list().map { it.toDto() }
    }

    @PostMapping
    fun createStatus(@RequestBody statusEntity: StatusDto): ResponseEntity<StatusDto> {
        return try {
            val createdStatus = statusService.create(statusEntity.toEntity()).toDto()
            ResponseEntity(createdStatus, HttpStatus.CREATED)

        } catch (e: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        } catch (e: DataIntegrityViolationException) {
            ResponseEntity(HttpStatus.CONFLICT)
        }
    }
}