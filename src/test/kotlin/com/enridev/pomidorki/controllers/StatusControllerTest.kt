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
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

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

    @Test
    fun `test that create status creates status and returns HTTP 201`() {
        every {
            statusService.create(any())
        } answers {
            firstArg()
        }

        mockkMvc.post(STATUS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testStatusEntityA()
            )
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `test that create status returns HTTP 400 when IllegalArgumentException is thrown`() {
        every {
            statusService.create(any())
        } throws(IllegalArgumentException("ID must be null"))

        mockkMvc.post(STATUS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testStatusEntityA(1)
            )
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `test that create status returns HTTP 409 when DataIntegrityViolationException is thrown`() {
        every {
            statusService.create(any())
        } throws(DataIntegrityViolationException("Status name must be unique"))

        mockkMvc.post(STATUS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testStatusEntityA()
            )
        }.andExpect {
            status { isConflict() }
        }
    }

    @Test
    fun `test that read one status returns status and HTTP 200 when status in the database`() {
        every {
            statusService.get(any())
        } answers {
            testStatusEntityA(999)
        }

        mockkMvc.get("$STATUS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.id", Matchers.equalTo(999))
            jsonPath("$.name", Matchers.equalTo("In progress"))
        }
    }

    @Test
    fun `test that read one status returns HTTP 404 when no status in the database`() {
        every {
            statusService.get(any())
        } answers {
            null
        }

        mockkMvc.get("$STATUS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNotFound() }
        }
    }

    @Test
    fun `test that status full update updates status and returns HTTP 200 on successful update`() {
        every {
            statusService.fullUpdate(any(), any())
        } answers { secondArg() }

        mockkMvc.put("$STATUS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(testStatusEntityA(999))
        }.andExpect {
            status { isOk() }
            content { jsonPath("$.id", Matchers.equalTo(999)) }
            content { jsonPath("$.name", Matchers.equalTo("In progress")) }
        }
    }

    @Test
    fun `test that status full update returns HTTP 400 when IllegalStateException is thrown`() {
        every {
            statusService.fullUpdate(any(), any())
        } throws (IllegalStateException())

        mockkMvc.put("$STATUS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
        }
    }
}