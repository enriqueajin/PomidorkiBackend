package com.enridev.pomidorki.domain.entities

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "users")
data class UserEntity(

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", allocationSize = 1)
    val id: Long?,

    @Column(name = "display_name", nullable = false)
    val name: String,

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "password_hash", nullable = false)
    val password: String,

    @Column(name = "streak_count", nullable = false)
    val streakCount: Int,

    @Column(name = "last_pomodoro_date")
    val lastPomodoroDate: OffsetDateTime?,

    @Column(name = "avatar_url")
    val avatarUrl: String?,

    @Column(name = "firebase_uid", nullable = false, unique = true)
    val firebaseUid: String,

    @Column(name = "is_active", nullable = false)
    val isActive: Boolean,

    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: OffsetDateTime
) {
    @PrePersist
    protected fun onCreate() {
        this.createdAt = OffsetDateTime.now()
        this.updatedAt = OffsetDateTime.now()
    }

    @PreUpdate
    protected fun onUpdate() {
        this.updatedAt = OffsetDateTime.now()
    }
}
