package com.enridev.pomidorki.controllers

import com.enridev.pomidorki.services.StatusService
import com.enridev.pomidorki.testStatusEntityA
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

private const val STATUS_BASE_URL = "/v1/status"

@SpringBootTest
@AutoConfigureMockMvc
class StatusControllerTest @Autowired constructor(
    private val mockkMvc: MockMvc,
    @MockkBean val statusService: StatusService
) {

    val objectMapper = ObjectMapper()

    @Test
    fun `test that read many status returns empty list and HTTP 200 when no status present in the database`() {

        every {
            statusService.list()
        } answers {
            emptyList()
        }

        mockkMvc.get(STATUS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { json("[]") }
        }
    }

    @Test
    fun `test that read many status returns status and HTTP 200 when status present in the database`() {

        every {
            statusService.list()
        } answers {
            listOf(testStatusEntityA(1))
        }

        mockkMvc.get(STATUS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { jsonPath("$[0].id", Matchers.equalTo(1)) }
            content { jsonPath("$[0].name", Matchers.equalTo("In progress")) }
        }
    }
}