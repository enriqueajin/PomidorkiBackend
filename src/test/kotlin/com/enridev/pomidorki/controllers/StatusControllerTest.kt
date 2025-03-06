package com.enridev.pomidorki.controllers

import com.enridev.pomidorki.services.StatusService
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class StatusControllerTest @Autowired constructor(
    private val mockkMvc: MockMvc,
    @MockkBean val statusService: StatusService
) {

    val objectMapper = ObjectMapper()
}