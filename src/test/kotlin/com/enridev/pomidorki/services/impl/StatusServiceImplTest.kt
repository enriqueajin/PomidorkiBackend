package com.enridev.pomidorki.services.impl

import com.enridev.pomidorki.domain.StatusUpdateRequest
import com.enridev.pomidorki.repositories.StatusRepository
import com.enridev.pomidorki.testStatusEntityA
import com.enridev.pomidorki.testStatusEntityB
import com.enridev.pomidorki.testStatusRequestA
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
@Transactional
class StatusServiceImplTest @Autowired constructor(
    private val underTest: StatusServiceImpl,
    private val statusRepository: StatusRepository
) {

    @Test
    fun `test that list status return an empty list when no status in the database`() {
        val statusList = underTest.list()
        assertThat(statusList).isEmpty()
    }

    @Test
    fun `test that list status return a list of status when status present in the database`() {
        val savedStatus = statusRepository.save(testStatusEntityA())
        val expected = listOf(savedStatus)
        val statusList = underTest.list()
        assertThat(statusList).isNotEmpty()
        assertThat(statusList).isEqualTo(expected)
    }

    @Test
    fun `test that create status with ID throws IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> {
            val existingStatus = testStatusEntityA(333)
            underTest.create(existingStatus)
        }
    }

    @Test
    fun `test that create status persists the status in the database`() {
        val savedStatus = statusRepository.save(testStatusEntityA())
        assertThat(savedStatus.id).isNotNull()

        val retrievedStatus = statusRepository.findByIdOrNull(savedStatus.id!!)
        assertThat(retrievedStatus).isNotNull()

        assertThat(retrievedStatus!!).isEqualTo(
            testStatusEntityA(savedStatus.id)
        )
    }

    @Test
    fun `test that get status returns status when status present in the database`() {
        val savedStatus = statusRepository.save(testStatusEntityA())
        val result = underTest.get(savedStatus.id!!)
        assertThat(result).isEqualTo(savedStatus)
    }

    @Test
    fun `test that get status returns null when status not present in the database`() {
        val result = underTest.get(248)
        assertThat(result).isNull()
    }

    @Test
    fun `test that status full update throws IllegalStateException when id does not exists in the database`() {
        assertThrows<IllegalStateException> {
            val nonExistingId = 44
            val updatedStatus = testStatusEntityB()

            underTest.fullUpdate(nonExistingId, updatedStatus)
        }
    }

    @Test
    fun `test that status full update updates the status in the database`() {
        val existingStatus = statusRepository.save(testStatusEntityA())
        val existingStatusId = existingStatus.id!!
        val updatedStatus = testStatusEntityB(id = existingStatusId)
        val result = underTest.fullUpdate(existingStatusId, updatedStatus)
        assertThat(result).isEqualTo(updatedStatus)

        val retrievedStatus = statusRepository.findByIdOrNull(existingStatusId)
        assertThat(retrievedStatus).isNotNull()
        assertThat(retrievedStatus).isEqualTo(updatedStatus)
    }

    @Test
    fun `test that partial update throws IllegalStateException when status with id does not exists in the database`() {
        assertThrows<IllegalStateException> {
            val nonExistingId = 44
            val updatedStatus = testStatusRequestA()

            underTest.partialUpdate(nonExistingId, updatedStatus)
        }
    }

    @Test
    fun `test that partial update does not update the status when all values are null`() {
        val existingStatus = statusRepository.save(testStatusEntityA())
        val existingStatusId = existingStatus.id!!
        val updatedStatus = underTest.partialUpdate(existingStatusId, StatusUpdateRequest())
        assertThat(updatedStatus).isEqualTo(existingStatus)
    }

    @Test
    fun `test that partial update updates name`() {
        val newName = "New status"
        val existingStatus = testStatusEntityA()
        val expectedStatus = existingStatus.copy(
            name = newName
        )
        val statusUpdateRequest = StatusUpdateRequest(
            name = newName
        )

        val savedExistingStatus = statusRepository.save(existingStatus)
        val existingStatusId = savedExistingStatus.id!!

        val updatedStatus = underTest.partialUpdate(savedExistingStatus.id!!, statusUpdateRequest)

        val expected = expectedStatus.copy(id = existingStatusId)
        assertThat(updatedStatus).isEqualTo(expected)

        val retrievedStatus = statusRepository.findByIdOrNull(existingStatusId)
        assertThat(retrievedStatus).isNotNull()
        assertThat(retrievedStatus).isEqualTo(expected)
    }
}
