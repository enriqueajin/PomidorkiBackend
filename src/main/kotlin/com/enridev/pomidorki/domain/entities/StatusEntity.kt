package com.enridev.pomidorki.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "status")
data class StatusEntity(
    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_id_seq")
    @SequenceGenerator(name = "status_id_seq", allocationSize = 1)
    val id: Int?,

    @Column(nullable = false, unique = true)
    val name: String
)