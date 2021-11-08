package org.alki.lupus.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * DB task representation, created to decouple view and storage
 */
@Entity
data class TaskRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val date_start: Long,
    val date_finish: Long,
    val name: String,
    var description: String
)