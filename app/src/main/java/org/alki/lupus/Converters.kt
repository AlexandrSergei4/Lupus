package org.alki.lupus

import org.alki.lupus.db.entity.TaskRecord
import org.alki.lupus.domain.model.Task
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

fun TaskRecord.toTask(): Task {
    return Task(
        this.id!!,
        this.date_start.toLocalDate(),
        this.date_finish.toLocalDate(),
        this.name,
        this.description
    )
}

fun Task.toTaskRecord(): TaskRecord {
    return TaskRecord(
        null,
        this.date_start.toLong(),
        this.date_finish.toLong(),
        this.name,
        this.description
    )
}

fun LocalDate.toLong(): Long = Timestamp.from(this.atStartOfDay(ZoneId.of("UTC")).toInstant()).time
fun Long.toLocalDate(): LocalDate = Instant.ofEpochMilli(this).atZone(ZoneId.of("UTC")).toLocalDate()
