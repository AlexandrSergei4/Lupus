package org.alki.lupus.domain.model

import java.time.LocalDateTime

/**
 * Displayed task representation, created to decouple view and storage
 */
data class Task(
    val id: Long?,
    val date_start: LocalDateTime,
    val date_finish: LocalDateTime,
    val name: String,
    var description: String
)