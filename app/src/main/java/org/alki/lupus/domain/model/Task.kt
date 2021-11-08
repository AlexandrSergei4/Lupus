package org.alki.lupus.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Displayed task representation, created to decouple view and storage
 */
data class Task(
    val id: Long?,
    val date_start: LocalDate,
    val date_finish: LocalDate,
    val name: String,
    var description: String
)