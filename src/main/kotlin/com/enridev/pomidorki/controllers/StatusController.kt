package com.enridev.pomidorki.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/status"])
class StatusController {

    @GetMapping
    fun readManyStatus(): List<String> {
        return listOf("To-do", "In progress", "Done")
    }
}