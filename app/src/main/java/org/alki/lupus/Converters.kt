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
        LocalDateTime.from(Timestamp(this.date_start).toInstant()),
        LocalDateTime.from(Timestamp(this.date_finish).toInstant()),
        this.name,
        this.description
    )
}

fun Task.toTaskRecord(): TaskRecord {
    return TaskRecord(
        null,
        Timestamp.from(this.date_start.atZone(ZoneId.of("UTC")).toInstant()).time,
        Timestamp.from(this.date_finish.atZone(ZoneId.of("UTC")).toInstant()).time,
        this.name,
        this.description
    )
}

fun LocalDate.toLong(): Long = Timestamp.from(this.atStartOfDay(ZoneId.of("UTC")).toInstant()).time
