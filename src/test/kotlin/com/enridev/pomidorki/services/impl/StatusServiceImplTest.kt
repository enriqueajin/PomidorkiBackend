package com.enridev.pomidorki.services.impl

import com.enridev.pomidorki.repositories.StatusRepository
import com.enridev.pomidorki.testStatusEntityA
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

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

}