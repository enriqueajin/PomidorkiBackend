package com.enridev.pomidorki.controllers

import com.enridev.pomidorki.domain.dto.StatusDto
import com.enridev.pomidorki.domain.dto.StatusUpdateRequestDto
import com.enridev.pomidorki.services.StatusService
import com.enridev.pomidorki.toDto
import com.enridev.pomidorki.toEntity
import com.enridev.pomidorki.toStatusUpdateRequest
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

    @GetMapping(path = ["/{id}"])
    fun readOneStatus(@PathVariable("id") statusId: Int): ResponseEntity<StatusDto> {
        val retrievedStatus = statusService.get(statusId)?.toDto()
        return retrievedStatus?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping(path = ["/{id}"])
    fun fullUpdateStatus(
        @PathVariable("id") statusId: Int,
        @RequestBody statusDto: StatusDto
    ): ResponseEntity<StatusDto> {
        return try {
            val updatedStatus = statusService.fullUpdate(statusId, statusDto.toEntity()).toDto()
            ResponseEntity(updatedStatus, HttpStatus.OK)
        } catch(e: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/{id}")
    fun partialUpdateStatus(
        @PathVariable("id") statusId: Int,
        @RequestBody statusUpdateRequestDto: StatusUpdateRequestDto
    ): ResponseEntity<StatusDto> {
        return try {
            val updatedStatus = statusService.partialUpdate(statusId, statusUpdateRequestDto.toStatusUpdateRequest())
            ResponseEntity(updatedStatus.toDto(), HttpStatus.OK)
        } catch(e: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}