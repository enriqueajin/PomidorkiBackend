package com.enridev.pomidorki.services.impl

import com.enridev.pomidorki.repositories.StatusRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Transactional
class StatusServiceImplTest @Autowired constructor(
    private val underTest: StatusServiceImpl,
    private val statusRepository: StatusRepository
) {

}