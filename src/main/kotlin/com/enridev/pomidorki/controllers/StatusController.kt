package com.enridev.pomidorki.controllers

import com.enridev.pomidorki.domain.dto.StatusDto
import com.enridev.pomidorki.services.StatusService
import com.enridev.pomidorki.toDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/status"])
class StatusController(private val statusService: StatusService) {

    @GetMapping
    fun readManyStatus(): List<StatusDto> {
        return statusService.list().map { it.toDto() }
    }
}